package com.cinema.CineConnect.model.DTO;

import java.util.UUID;

public record UserRecordRoleId(
        String name,
        String email,
        String password,
        Integer role,
        String birth_date

){}

