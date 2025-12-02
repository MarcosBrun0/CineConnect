package com.cinema.CineConnect.model.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record StoreItemRecord(
        UUID productId,
        String name,
        String type,
        BigDecimal price,
        UUID sessionId,
        String image_url,
        List<ProductRecord> addOns
) {
}
