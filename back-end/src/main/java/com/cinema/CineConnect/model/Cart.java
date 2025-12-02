package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class Cart {
    private UUID id;
    private UUID userId;
    private List<CartItem> items = new ArrayList<>();
    private BigDecimal price = BigDecimal.ZERO;
    private String name;

    public Cart() {
    }

    public Cart(UUID id, UUID userId, List<CartItem> items, BigDecimal price, String name) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.price = price;
        this.name = name;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
