package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {

        this.jdbcClient = jdbcClient;
    }


    public List<com.cinema.CineConnect.model.DTO.UserRecord> findAll() {
        return jdbcClient.sql("SELECT name, email FROM client")
                .query(com.cinema.CineConnect.model.DTO.UserRecord.class)
                .list();
    }


    public void saveClient(UserRecord user) {
        jdbcClient.sql("""
INSERT INTO users (name, role_id, email, password, birth_date)       VALUES (:nome,:email)
""")
                .param("nome", user.nome())
                .param("email", user.email())
                .update();
    }

    public record UserRecord(String nome, String email) {

    }



}
