package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Cart;
import com.cinema.CineConnect.model.CartItem;
import com.cinema.CineConnect.model.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CartRepository {
    JdbcClient jdbcClient;
    CartRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private void createAddon(JdbcClient jdbcClient, UUID cartItemId, Product addon) {
        jdbcClient.sql("""
        INSERT INTO addons (cart_item_id, product_id, quantity, price_at_time)
        VALUES (:cartItemId, :productId, :quantity, :priceAtTime)
    """)
                .param("cartItemId", cartItemId)
                .param("productId", addon.getProductProductId())
                .param("quantity", 1) // default quantity 1, can extend if needed
                .param("priceAtTime", addon.getProductPrice())
                .update();
    }


    private UUID createCartItem(JdbcClient jdbcClient, UUID cartId, CartItem item) {
        // Insert the cart item and get its generated UUID
        UUID cartItemId = jdbcClient.sql("""
        INSERT INTO cart_items (cart_id, product_id, quantity, price_at_time)
        VALUES (:cartId, :productId, :quantity, :priceAtTime)
        RETURNING id
    """)
                .param("cartId", cartId)
                .param("productId", item.getProduct().getProductProductId())
                .param("quantity", item.getQuantity())
                .param("priceAtTime", item.getTotalPrice())
                .query(UUID.class)
                .single();

        // Insert all addons for this cart item
        for (Product addon : item.getAddons()) {
            createAddon(jdbcClient, cartItemId, addon);
        }

        return cartItemId;
    }


    public UUID createCart(JdbcClient jdbcClient, Cart cart) {
        // Insert the cart and get the generated ID
        UUID cartId = jdbcClient.sql("""
        INSERT INTO carts (user_id, price, name)
        VALUES (:userId, :price, :name)
        RETURNING id
    """)
                .param("userId", cart.getUserId())
                .param("price", cart.getPrice())
                .param("name", "Cart of user " + cart.getUserId()) // optional name
                .query(UUID.class)
                .single();

        // Insert items
        for (CartItem item : cart.getItems()) {
            createCartItem(jdbcClient, cartId, item);
        }

        return cartId;
    }

}
