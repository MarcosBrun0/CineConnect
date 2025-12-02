package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.BookingRequest;
import com.cinema.CineConnect.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/occupied/{sessionId}")
    public List<String> getOccupied(@PathVariable Long sessionId) {
        return ticketService.getOccupiedSeats(sessionId);
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookTickets(@RequestBody BookingRequest request) {
        try {
            ticketService.bookTickets(request);
            return ResponseEntity.ok("Reserva realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}