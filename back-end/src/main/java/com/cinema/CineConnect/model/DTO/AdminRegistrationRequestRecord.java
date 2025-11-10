package com.cinema.CineConnect.model.DTO;


import java.time.LocalDate;

public record AdminRegistrationRequestRecord(
        String email,
        String password,
        String name,
        LocalDate birth_date,
        String role
) {}
