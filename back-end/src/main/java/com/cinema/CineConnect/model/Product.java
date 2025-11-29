package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    UUID productId;
    String name;
    String type;
    BigDecimal price;

    public Product(UUID productId, String name, String type, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.price = price;
    }
    public String getProductName() {
        return name;
    }
    public String getProductType() {
        return type;
    }
    public BigDecimal getProductPrice() {return price;
    }
    public void setProductPrice(BigDecimal price) {
        this.price = price;
    }
    public UUID getProductProductId(){
        return productId;
    }

}
