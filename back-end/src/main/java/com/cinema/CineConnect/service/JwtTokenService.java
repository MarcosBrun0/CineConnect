package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;

    JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public Jwt generate(LoginRequestRecord loginRequestRecord, Long expiresIn) {
        var email = loginRequestRecord.email();
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("https://cineconnect-server.com")
                .subject(email)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }
}