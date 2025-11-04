package com.cinema.CineConnect.model.DTO;

import java.util.UUID;

public record UserRecordRoleName(
        UUID id,
        String name,
        String email,
        String password,
        String roleName,
        String birth_date

){}

