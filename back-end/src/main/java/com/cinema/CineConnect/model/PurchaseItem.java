package com.cinema.CineConnect.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseItem {
    private UUID id;
    private UUID purchaseId;
    private UUID productId;
    private int quantity;
    private BigDecimal priceAtPurchase;
    private String name;
    private String imageUrl;
    private List<PurchaseItem> addons = new ArrayList<>(); // Using PurchaseItem for addons too for simplicity

    public PurchaseItem(UUID productId, int quantity, BigDecimal priceAtPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }
}
