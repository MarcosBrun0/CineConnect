package com.cinema.CineConnect.model.DTO;

public record LoginResponseRecord(org.springframework.security.oauth2.jwt.Jwt accessToken, long expiresIn) {

}
