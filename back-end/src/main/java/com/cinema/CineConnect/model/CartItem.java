package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartItem {
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;
    private List<Product> addons;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.addons = new ArrayList<>();
        this.totalPrice = product.getProductPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void addAddon(Product addon) {
        addons.add(addon);
        totalPrice = totalPrice.add(addon.getProductPrice());
    }

    public void removeAddon(Product addon) {
        if (addons.remove(addon)) {
            totalPrice = totalPrice.subtract(addon.getProductPrice());
        }
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getAddons() {
        return new ArrayList<>(addons);
    }
}
