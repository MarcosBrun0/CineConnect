package com.cinema.CineConnect.model;

import java.util.UUID;

public abstract class Product {
    UUID productId;
    String name;
    String type;
    Float price;

    public Product(UUID productId, String name, String type, Float price) {
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
    public Float getProductPrice() {
        return price;
    }
    public void setProductPrice(Float price) {
        this.price = price;
    }
    public UUID getProductProductId(){
        return productId;
    }

}
