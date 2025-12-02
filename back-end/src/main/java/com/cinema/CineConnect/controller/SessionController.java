package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.SessionResponse;
import com.cinema.CineConnect.repository.SessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "*") // Permite acesso do React
public class SessionController {

    private final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // Endpoint 1: Retorna lista de sessões de um filme
    // URL: http://localhost:8080/sessions/movie/{id}
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<SessionResponse>> getSessionsByMovie(@PathVariable Long movieId) {
        List<SessionResponse> sessions = sessionRepository.findByMovieId(movieId);

        if (sessions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sessions);
    }

    // Endpoint 2: Retorna dados de UMA sessão específica
    // URL: http://localhost:8080/sessions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable Long id) {
        return sessionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}