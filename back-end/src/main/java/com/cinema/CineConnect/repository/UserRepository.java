package com.cinema.CineConnect.repository;

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
        return jdbcClient.sql("SELECT id, nome, email FROM usuario")
                .query(com.cinema.CineConnect.model.DTO.UserRecord.class)
                .list();
    }


    public void save(UserRecord user) {
        jdbcClient.sql("""
        INSERT INTO usuario(nome, email)
        VALUES (:nome,:email)
""")
                .param("nome", user.nome())
                .param("email", user.email())
                .update();
    }

    public record UserRecord(String nome, String email) {

    }

    public Optional<com.cinema.CineConnect.model.DTO.UserRecord> findByEmail(String email) {
        String sql = "SELECT id, nome, email FROM usuario WHERE email = :email";
        // .optional() retorna um Optional.empty() se nenhum resultado for encontrado
        return jdbcClient.sql(sql)
                .param("email", email)
                .query(com.cinema.CineConnect.model.DTO.UserRecord.class)
                .optional();
    }


}
