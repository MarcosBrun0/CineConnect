package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    UUID productId;
    String name;
    String type;
    BigDecimal price;
    int quantity;
    boolean available;

    public Product(UUID productId, String name, String type, BigDecimal price, int quantity, boolean available) {
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public String getProductName() {
        return name;
    }

    public String getProductType() {
        return type;
    }

    public BigDecimal getProductPrice() {
        return price;
    }

    public void setProductPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getProductProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
