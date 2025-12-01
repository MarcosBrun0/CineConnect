package com.cinema.CineConnect.model;

import com.cinema.CineConnect.model.DTO.ProductRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodProduct extends Product {

    private List<FoodProduct> addOns;

    public FoodProduct(UUID productId, String name, String type, BigDecimal price, int quantity, boolean available) {
        super(productId, name, type, price, quantity, available);
        this.addOns = new ArrayList<>();
    }

    public FoodProduct(ProductRecord record) {
        super(record.productId(), record.name(), record.type(), record.price(), record.quantity(), record.available());
        this.addOns = new ArrayList<>();

        if (record.addOns() != null && !record.addOns().isEmpty()) {
            for (ProductRecord addOn : record.addOns()) {
                this.addOns.add(new FoodProduct(addOn));
            }
        }
    }

    public List<FoodProduct> getAddOns() {
        return addOns;
    }
}
