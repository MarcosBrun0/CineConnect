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
     * Salva um novo usuário (INSERT)
     */
    public Usuario save(Usuario usuario) {
        var keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO usuario (nome, email) VALUES (:nome, :email)")
                .param("nome", usuario.nome())
                .param("email", usuario.email())
                .update(keyHolder, "id"); // informa a coluna da PK

        Long novoId = Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .orElseThrow(() -> new RuntimeException("Falha ao obter ID gerado"));

        return new Usuario(novoId.intValue(), usuario.nome(), usuario.email());
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
     */
    public Optional<Usuario> findById(Integer id) {
        // Usa `query(Usuario.class).optional()` para retornar um Optional
        return jdbcClient.sql("SELECT id, nome, email FROM usuario WHERE id = :id")
                .param("id", id)
                .query(Usuario.class)
                .optional();
    }

    /**
     * Atualiza um usuário (UPDATE)
     */
    public int update(Usuario usuario) {
        // Usa `update()` para retornar o número de linhas afetadas
        return jdbcClient.sql("UPDATE usuario SET nome = :nome, email = :email WHERE id = :id")
                .param("nome", usuario.nome())
                .param("email", usuario.email())
                .param("id", usuario.id())
                .update();
    }

    /**
     * Deleta um usuário por ID (DELETE)
     */
    public int deleteById(Integer id) {
        // Usa `update()` para retornar o número de linhas afetadas
        return jdbcClient.sql("DELETE FROM usuario WHERE id = :id")
                .param("id", id)
                .update();
    }
}