package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcClient jdbcClient;

    // O JdbcClient é injetado automaticamente pelo Spring Boot
    public UsuarioRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * Busca todos os usuários (SELECT * )
     */
    public List<Usuario> findAll() {
        // Usa `query(Usuario.class).list()` para mapear automaticamente o resultado
        return jdbcClient.sql("SELECT id, nome, email FROM usuario")
                .query(Usuario.class)
                .list();
    }

    /**
     * Busca um usuário por ID (SELECT WHERE)
//     */
//    public Optional<Usuario> findById(Integer id) {
//        // Usa `query(Usuario.class).optional()` para retornar um Optional
//        return jdbcClient.sql("SELECT id, nome, email FROM usuario WHERE id = :id")
//                .param("id", id)
//                .query(Usuario.class)
//                .optional();
//    }


}