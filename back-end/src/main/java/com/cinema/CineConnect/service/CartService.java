package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.Cart;
import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.repository.CartRepository;
import com.cinema.CineConnect.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart getCart(UUID userId) {
        return getCartDetails(getOrCreateCartId(userId));
    }

    @Transactional
    public void addItemToCart(UUID userId, UUID productId, Integer quantity, List<UUID> addonIds) {
        UUID cartId = getOrCreateCartId(userId);

        ProductRecord product = productRepository.findById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }

        UUID cartItemId = cartRepository.addItem(cartId, productId, quantity, product.price());

        if (addonIds != null && !addonIds.isEmpty()) {
            addAddonsToItem(cartItemId, addonIds);
        }

        cartRepository.updateCartPrice(cartId);
    }

    @Transactional
    public void updateItemQuantity(UUID userId, UUID cartItemId, int quantity) {
        UUID cartId = getOrCreateCartId(userId); // We need cartId to update price. Ideally pass it or fetch it.
        // Optimization: Fetch cartId from cartItemId if not readily available, or just
        // fetch user's cart.
        // Since we have userId, we can get the cartId.

        if (quantity <= 0) {
            cartRepository.removeItem(cartItemId);
        } else {
            cartRepository.updateItemQuantity(cartItemId, quantity);
        }
        cartRepository.updateCartPrice(cartId);
    }

    @Transactional
    public void removeItem(UUID userId, UUID cartItemId) {
        UUID cartId = getOrCreateCartId(userId);
        cartRepository.removeItem(cartItemId);
        cartRepository.updateCartPrice(cartId);
    }

    @Transactional
    public void clearCart(UUID userId) {
        System.out.println("CartService: Clearing cart for user " + userId);
        UUID cartId = getOrCreateCartId(userId);
        System.out.println("CartService: Cart ID is " + cartId);
        cartRepository.clearCart(cartId);
        cartRepository.updateCartPrice(cartId);
        System.out.println("CartService: Cart cleared and price updated");
    }

    private UUID getOrCreateCartId(UUID userId) {
        UUID cartId = cartRepository.findByUserId(userId);
        if (cartId == null) {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setPrice(BigDecimal.ZERO);
            newCart.setName("User Cart");
            cartId = cartRepository.createCart(newCart);
        }
        return cartId;
    }

    private Cart getCartDetails(UUID cartId) {
        return cartRepository.getCartDetails(cartId);
    }

    private void addAddonsToItem(UUID cartItemId, List<UUID> addonIds) {
        for (UUID addonId : addonIds) {
            ProductRecord addon = productRepository.findById(addonId);
            if (addon != null) {
                cartRepository.addAddon(cartItemId, addonId, 1, addon.price());
            }
        }
    }
}
