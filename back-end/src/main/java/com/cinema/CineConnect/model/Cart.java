package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID userId;
    private List<CartItem> items;
    private BigDecimal price;

    public Cart(UUID userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public void addItem(CartItem item) {
        items.add(item);
        price = price.add(item.getTotalPrice());
    }

    public void removeItem(CartItem item) {
        if (items.remove(item)) {
            price = price.subtract(item.getTotalPrice());
        }
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID getUserId() {
        return userId;
    }
}
