package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.BookingRequest;
import com.cinema.CineConnect.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<String> getOccupiedSeats(Long sessionId) {
        return ticketRepository.findOccupiedSeats(sessionId);
    }

    @Transactional // Importante: Se um assento falhar, cancela tudo
    public void bookTickets(BookingRequest request) {
        List<String> occupied = ticketRepository.findOccupiedSeats(request.sessionId());

        for (String seat : request.seats()) {
            if (occupied.contains(seat)) {
                throw new IllegalArgumentException("O assento " + seat + " já está ocupado.");
            }
            // Salva o ticket
            ticketRepository.saveTicket(request.sessionId(), seat, request.clientName());
        }
    }
}