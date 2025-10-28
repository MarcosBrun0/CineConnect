package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.ClientRecord;
import com.cinema.CineConnect.model.DTO.EmployeeRecord;
import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
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
    public Optional<LoginRequestRecord> findByEmail(String email) {
        String sql = "SELECT name, email,birth_date FROM client WHERE email = :email";
        // .optional() retorna um Optional.empty() se nenhum resultado for encontrado
        return jdbcClient.sql(sql)
                .param("email", email)
                .query(LoginRequestRecord.class)
                .optional();
    }

//    //tenta encontrar email na tabela de employee
//    public Optional<EmployeeRecord> findEmployeeByEmail(String email) {
//        return jdbcClient.sql("SELECT * FROM client WHERE email = :email").param("email",email)
//                .query(EmployeeRecord.class).optional();
//    }

}
