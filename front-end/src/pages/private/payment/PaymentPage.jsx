import React, { useState, useEffect } from "react";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import api from "../../../api";
import CheckoutForm from "../../../components/CheckoutForm";
import { useCart } from "../../../context/CartContext";

// Make sure to call loadStripe outside of a component’s render to avoid
// recreating the Stripe object on every render.
// This is your test publishable API key.
const stripePromise = loadStripe(import.meta.env.VITE_STRIPE_PUBLISHABLE_KEY);

export function PaymentPage() {
    const [clientSecret, setClientSecret] = useState("");
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    const { cart } = useCart();

    useEffect(() => {
        // Create PaymentIntent as soon as the page loads
        const createPaymentIntent = async () => {
            if (cart.length === 0) {
                setIsLoading(false);
                return;
            }

            try {
                setIsLoading(true);
                // Map cart to ProductRecord format expected by backend
                // Map cart to ProductRecord format expected by backend
                const productRecords = cart.map(item => ({
                    productId: item.product.id,
                    name: item.product.name,
                    type: item.product.type,
                    price: item.product.price,
                    sessionId: item.product.sessionId,
                    imageUrl: item.product.imageUrl,
                    addOns: item.addons ? item.addons.map(addon => ({
                        productId: addon.id,
                        name: addon.name,
                        type: addon.type,
                        price: addon.price,
                        imageUrl: addon.imageUrl
                    })) : []
                }));

                const response = await api.post("/api/stripe/create-payment-intent", productRecords);
                setClientSecret(response.data);
                setIsLoading(false);
            } catch (err) {
                console.error("Error creating payment intent:", err);
                setError("Failed to initialize payment. Please try again.");
                setIsLoading(false);
            }
        };

        createPaymentIntent();
    }, [cart]);

    const appearance = {
        theme: 'stripe',
    };
    const options = {
        clientSecret,
        appearance,
    };

    if (isLoading) {
        return <div className="p-8 text-center text-lg">Loading Payment...</div>;
    }

    if (error) {
        return <div className="p-8 text-center text-lg text-red-600">Error: {error}</div>;
    }

    if (!clientSecret) {
        return <div className="p-8 text-center text-lg">Cart is empty or payment initialization failed.</div>;
    }

    return (
        <div className="p-8 flex flex-col items-center">
            <h1 className="text-2xl font-bold mb-6">Payment</h1>

            {/* Cart Summary */}
            <div className="w-full max-w-md mb-8 bg-white p-4 rounded-lg shadow border border-gray-200">
                <h2 className="text-lg font-semibold mb-4">Order Summary</h2>
                <div className="space-y-4">
                    {cart.map((item, index) => (
                        <div key={index} className="flex justify-between items-start border-b border-gray-100 pb-2 last:border-0">
                            <div className="flex-1">
                                <div className="font-medium">{item.product.name}</div>
                                <div className="text-sm text-gray-500">Qty: {item.quantity}</div>
                                {item.addons && item.addons.length > 0 && (
                                    <div className="text-xs text-gray-400 mt-1">
                                        {item.addons.map(a => `+ ${a.name}`).join(', ')}
                                    </div>
                                )}
                            </div>
                            <div className="font-medium">
                                ${(item.product.price * item.quantity + (item.addons ? item.addons.reduce((sum, a) => sum + a.price, 0) : 0)).toFixed(2)}
                            </div>
                        </div>
                    ))}
                </div>
                <div className="mt-4 pt-4 border-t border-gray-200 flex justify-between font-bold text-lg">
                    <span>Total</span>
                    <span>
                        ${cart.reduce((total, item) => {
                            const itemTotal = item.product.price * item.quantity;
                            const addonsTotal = item.addons ? item.addons.reduce((sum, a) => sum + a.price, 0) : 0;
                            return total + itemTotal + addonsTotal;
                        }, 0).toFixed(2)}
                    </span>
                </div>
            </div>

            {clientSecret && (
                <Elements options={options} stripe={stripePromise}>
                    <CheckoutForm />
                </Elements>
            )}
        </div>
    );
}

export default PaymentPage;