import React, { createContext, useState, useContext, useEffect } from 'react';
import api from '../api';

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState([]);
    const [loading, setLoading] = useState(false);

    const fetchCart = async () => {
        try {
            setLoading(true);
            const response = await api.get('/api/cart');
            setCart(response.data.items || []);
        } catch (error) {
            console.error("Failed to fetch cart", error);
            if (error.response && error.response.status === 401) {
                setCart([]);
            }
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCart();
    }, []);

    const addToCart = async (product, quantity = 1, addonIds = []) => {
        if (!product.available) {
            alert("This product is currently unavailable.");
            return;
        }
        try {
            await api.post('/api/cart/items', {
                productId: product.productId,
                quantity,
                addonIds
            });
            fetchCart();
        } catch (error) {
            console.error("Error adding to cart", error);
            alert("Failed to add to cart");
        }
    };

    const removeFromCart = async (cartItemId) => {
        try {
            await api.delete(`/api/cart/items/${cartItemId}`);
            fetchCart();
        } catch (error) {
            console.error("Error removing from cart", error);
        }
    };

    const updateQuantity = async (cartItemId, quantity) => {
        if (quantity <= 0) {
            removeFromCart(cartItemId);
            return;
        }
        try {
            await api.put(`/api/cart/items/${cartItemId}`, { quantity });
            fetchCart();
        } catch (error) {
            console.error("Error updating quantity", error);
        }
    };

    const clearCart = () => {
        setCart([]);
    };

    const getTotalPrice = () => {
        return cart.reduce((total, item) => {
            const itemTotal = item.product.price * item.quantity;
            const addonsTotal = item.addons ? item.addons.reduce((sum, addon) => sum + addon.price, 0) : 0;
            return total + itemTotal + addonsTotal;
        }, 0);
    };

    return (
        <CartContext.Provider value={{ cart, addToCart, removeFromCart, updateQuantity, clearCart, getTotalPrice, fetchCart }}>
            {children}
        </CartContext.Provider>
    );
};
