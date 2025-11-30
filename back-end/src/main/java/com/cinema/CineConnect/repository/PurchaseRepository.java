package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Purchase;
import com.cinema.CineConnect.model.PurchaseItem;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class PurchaseRepository {

    private final JdbcClient jdbcClient;

    public PurchaseRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Transactional
    public UUID save(Purchase purchase) {
        // 1. Insert Purchase
        UUID purchaseId = jdbcClient.sql("""
                    INSERT INTO purchases (user_id, payment_id, total_amount, status, created_at)
                    VALUES (:userId, :paymentId, :totalAmount, :status, :createdAt)
                    RETURNING id
                """)
                .param("userId", purchase.getUserId())
                .param("paymentId", purchase.getPaymentId())
                .param("totalAmount", purchase.getTotalAmount())
                .param("status", purchase.getStatus())
                .param("createdAt", purchase.getCreatedAt())
                .query(UUID.class)
                .single();

        purchase.setId(purchaseId);
        System.out.println("Purchase saved successfully for user: " + purchase.getUserId());
        // 2. Insert Items
        for (PurchaseItem item : purchase.getItems()) {
            item.setPurchaseId(purchaseId);
            saveItem(item);
        }

        return purchaseId;
    }

    private void saveItem(PurchaseItem item) {
        UUID itemId = jdbcClient.sql("""
                    INSERT INTO purchase_items (purchase_id, product_id, quantity, price_at_purchase)
                    VALUES (:purchaseId, :productId, :quantity, :priceAtPurchase)
                    RETURNING id
                """)
                .param("purchaseId", item.getPurchaseId())
                .param("productId", item.getProductId())
                .param("quantity", item.getQuantity())
                .param("priceAtPurchase", item.getPriceAtPurchase())
                .query(UUID.class)
                .single();

        // 3. Insert Addons
        if (item.getAddons() != null) {
            for (PurchaseItem addon : item.getAddons()) {
                jdbcClient.sql("""
                            INSERT INTO purchase_addons (purchase_item_id, product_id, quantity, price_at_purchase)
                            VALUES (:purchaseItemId, :productId, :quantity, :priceAtPurchase)
                        """)
                        .param("purchaseItemId", itemId)
                        .param("productId", addon.getProductId())
                        .param("quantity", addon.getQuantity())
                        .param("priceAtPurchase", addon.getPriceAtPurchase())
                        .update();
            }
        }
    }

    public void updateStatus(UUID purchaseId, String status) {
        jdbcClient.sql("UPDATE purchases SET status = :status WHERE id = :id")
                .param("status", status)
                .param("id", purchaseId)
                .update();
    }

    public void updatePaymentId(UUID purchaseId, String paymentId) {
        jdbcClient.sql("UPDATE purchases SET payment_id = :paymentId WHERE id = :id")
                .param("paymentId", paymentId)
                .param("id", purchaseId)
                .update();
    }
}
