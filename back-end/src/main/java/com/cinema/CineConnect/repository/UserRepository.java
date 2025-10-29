package com.cinema.CineConnect.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import com.cinema.CineConnect.model.DTO.UserRecord;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public List<UserRecord> findAllClients() {
        return jdbcClient.sql("SELECT name, email FROM users WHERE role_id = 5")
                .query(com.cinema.CineConnect.model.DTO.UserRecord.class)
                .list();
    }



    public void saveUser(UserRecord user) {
            jdbcClient.sql("""
    INSERT INTO users (name, role_id, email, password, birth_date)
        VALUES (:name,5,:email,:password,:birth_date)
    """)
                .param("name", user.name())
                .param("email", user.email())
                .param("password",user.password())
                .param("birth_date", LocalDate.parse(user.birth_date()))
                .update();
    }





}
