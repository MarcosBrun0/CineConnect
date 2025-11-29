package com.cinema.CineConnect.model.DTO;

import java.util.List;
import java.util.UUID;

public record ProductRecord(
        UUID productId,
        String name,
        String type,
        Float price,
        UUID sessionId,
        List<ProductRecord> addOns
) {
}

