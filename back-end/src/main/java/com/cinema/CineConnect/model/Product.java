package com.cinema.CineConnect.model;

public record Product(
        Integer product_id,
        String name,
        String type,
        Float price
) {
}
