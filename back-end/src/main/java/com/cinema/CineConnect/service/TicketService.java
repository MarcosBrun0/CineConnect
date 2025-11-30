package com.cinema.CineConnect.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    // Atualiza tickets/pedidos no banco de dados usando o sessionId do Stripe
    public void markTicketsAsPaid(String stripeSessionId) {
        // TODO: buscar pedidos pelo stripeSessionId no seu DB e marcar como pago
        System.out.println("Pagamento confirmado para sessão Stripe: " + stripeSessionId);
    }
}
