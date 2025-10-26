package com.cinema.CineConnect.model.DTO;

public record ProductRecord(
        Integer product_id,
        String name,
        String type,
        Float price
) {
}
