package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Usuario;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public List<Usuario> findAll() {
        return jdbcClient.sql("SELECT id, nome, email FROM usuario")
                .query(Usuario.class)
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

    @Bean
    ApplicationRunner runner(UserRepository userRepository){
        return args -> {
            var usuarioteste = new UserRecord("nome teste","email teste");
            userRepository.save(usuarioteste);
            System.out.println(userRepository.findAll());
        };
    }

}
