package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    JwtTokenService(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    public String generate(LoginRequestRecord loginRequestRecord, Long expiresIn) {
        var now = Instant.now();
        var userDb = userRepository.findByEmailRoleName(loginRequestRecord.email());
        if(userDb.isEmpty()) {
            throw new RuntimeException("Error fetching user data");
        }

        var claims = JwtClaimsSet.builder()
                .issuer("https://cineconnect-server.com")
                .subject(userDb.get().id().toString())
                .issuedAt(now)
                .claim("scope", userDb.get().roleName())
                .expiresAt(now.plusSeconds(expiresIn))
                .build();


        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));
        return jwt.getTokenValue();
    }


}