package com.cinema.CineConnect.model.DTO; // Or your actual package

import java.time.LocalDate;

public record ClientRecord(
        Integer id,
        String email,
        String password,
        String name,
        LocalDate birthDate
) {
}