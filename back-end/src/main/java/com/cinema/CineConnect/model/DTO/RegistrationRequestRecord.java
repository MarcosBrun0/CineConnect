package com.cinema.CineConnect.model.DTO;


import java.time.LocalDate;

public record RegistrationRequestRecord(
    String email,
    String password,
    String name,
    LocalDate birth_date
) {}

