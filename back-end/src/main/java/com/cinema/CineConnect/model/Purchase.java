package com.cinema.CineConnect.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Purchase {
    private UUID id;
    private UUID userId;
    private String paymentId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private List<PurchaseItem> items = new ArrayList<>();

    public Purchase() {
    }

    public Purchase(UUID userId, String paymentId, BigDecimal totalAmount, String status) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(PurchaseItem item) {
        items.add(item);
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }
}
