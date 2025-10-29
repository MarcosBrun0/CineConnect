package com.cinema.CineConnect.model.DTO;

public record UserRecord(
        String name,
        String email,
        String password,
        Integer role,
        String birth_date

){}

