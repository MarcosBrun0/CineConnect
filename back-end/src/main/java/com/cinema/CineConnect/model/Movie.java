package com.cinema.CineConnect.model;

public record Movie(
        Integer id,
        String name,
        Integer rating,
        String duration
) {
}
