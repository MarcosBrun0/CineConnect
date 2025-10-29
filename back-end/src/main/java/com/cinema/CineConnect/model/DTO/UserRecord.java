package com.cinema.CineConnect.model.DTO;

public record UserRecord(
        String name,
        String email,
        String password,
        Integer role_id,
        String birth_date

){}

