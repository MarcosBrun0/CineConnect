package com.cinema.CineConnect.model; // Or your actual package

import java.time.LocalDate;

public record Client(
        Integer id,
        String email,
        String password,
        String name,
        LocalDate birthDate
) {
}