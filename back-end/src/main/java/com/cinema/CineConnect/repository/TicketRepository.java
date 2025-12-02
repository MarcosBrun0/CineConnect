package com.cinema.CineConnect.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TicketRepository {

    private final JdbcClient jdbcClient;

    public TicketRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // Busca assentos já ocupados de uma sessão
    public List<String> findOccupiedSeats(Long sessionId) {
        return jdbcClient.sql("SELECT seat_number FROM tickets WHERE session_id = :sessionId")
                .param("sessionId", sessionId)
                .query(String.class)
                .list();
    }

    // Salva um ticket
    public void saveTicket(Long sessionId, String seat, String clientName) {
        jdbcClient.sql("INSERT INTO tickets (session_id, seat_number, client_name) VALUES (:sessionId, :seat, :name)")
                .param("sessionId", sessionId)
                .param("seat", seat)
                .param("name", clientName)
                .update();
    }
}