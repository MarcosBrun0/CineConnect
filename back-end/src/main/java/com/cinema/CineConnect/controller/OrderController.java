package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Order;
import com.cinema.CineConnect.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final TicketService ticketService;

    public OrderController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Retorna status do pedido para o frontend
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderStatus(
            @PathVariable String orderId,
            @AuthenticationPrincipal(expression = "userId") String userId) {

        Optional<Order> orderOpt = ticketService.getOrderByIdForUser(orderId, userId);
        return orderOpt.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.status(404).build());
    }
}
