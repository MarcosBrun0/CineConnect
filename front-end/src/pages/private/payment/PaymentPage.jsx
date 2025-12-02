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
            {clientSecret && (
                <Elements options={options} stripe={stripePromise}>
                    <CheckoutForm />
                </Elements>
            )}
        </div>
    );
}

export default PaymentPage;