package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.*;
import com.cinema.CineConnect.model.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class AuthRepository {
    private final JdbcClient jdbcClient;
    public AuthRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    //Tries to find account indo from the users folder
    public Optional<LoginRequestRecord> findByEmail(String email) {
        return jdbcClient.sql("SELECT email,password FROM users WHERE email = :email")
                .param("email", email)
                .query(LoginRequestRecord.class)
                .optional();
        // Returns optional.empty() if no user was found
    }


        // Returns optional.empty() if no user was found




}
