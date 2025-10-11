package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Client;
import com.cinema.CineConnect.model.Employee;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthRepository {
    private final JdbcClient jdbcClient;
    public AuthRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    //tenta encontrar email na tabela de client
    public Optional<Client> findClientByEmail(String email) {
        return jdbcClient.sql("SELECT  * FROM client WHERE email = :email")
                .param("email", email)
                .query(Client.class).optional();

    }
    //tenta encontrar email na tabela de employee
    public Optional<Employee> findEmployeeByEmail(String email) {
        return jdbcClient.sql("SELECT * FROM client WHERE email = :email").param("email",email)
                .query(Employee.class).optional();
    }

}
