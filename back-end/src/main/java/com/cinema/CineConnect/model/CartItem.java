package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class CartItem {
    private UUID id;
    private UUID cartId;
    private UUID productId;
    private Product product;
    private int quantity;
    private BigDecimal priceAtTime;
    private BigDecimal totalPrice;
    private List<Product> addons = new ArrayList<>();

    public CartItem() {
    }

    public CartItem(UUID id, UUID cartId, UUID productId, Product product, int quantity, BigDecimal priceAtTime,
            BigDecimal totalPrice, List<Product> addons) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.product = product;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
        this.totalPrice = totalPrice;
        this.addons = addons;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.addons = new ArrayList<>();
        this.totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void addAddon(Product addon) {
        addons.add(addon);
        // Recalculate total price based on quantity and addons
        // Base price * quantity + addons
        // Note: Usually addons are per item, so if quantity is > 1, addons might be
        // multiplied too.
        // Assuming addons are single instances added to the cart item which represents
        // a group of products.
        // However, the current logic seems to treat CartItem as a line item.
        // If I buy 2 Popcorns, and add 1 Butter, is it 1 Butter total or 1 Butter per
        // Popcorn?
        // The previous logic was: totalPrice = totalPrice.add(addon.getPrice());
        // This implies simple addition. I will stick to that but make it explicit.

        this.totalPrice = this.totalPrice.add(addon.getPrice());
    }

    public void removeAddon(Product addon) {
        if (addons.remove(addon)) {
            totalPrice = totalPrice.subtract(addon.getPrice());
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(BigDecimal priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getAddons() {
        return addons;
    }

    public void setAddons(List<Product> addons) {
        this.addons = addons;
    }
}
