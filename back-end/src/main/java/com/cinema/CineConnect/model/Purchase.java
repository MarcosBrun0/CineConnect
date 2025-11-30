package com.cinema.CineConnect.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    private UUID id;
    private UUID userId;
    private String paymentId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private List<PurchaseItem> items = new ArrayList<>();

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
}
