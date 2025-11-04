package com.cinema.CineConnect.model.DTO;


public record RegistrationRequestRecord(
    String email,
    String password,
    String name,
    String birthDate
) {}

