package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.ClientRecord;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ClientRepository {

    private final JdbcClient jdbcClient;
    public ClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<ClientRecord> findAll() {
        return jdbcClient.sql("SELECT * FROM client")
                .query(ClientRecord.class)
                .list();
    }

}
