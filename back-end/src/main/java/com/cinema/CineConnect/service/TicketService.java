package com.cinema.CineConnect.service;

import org.springframework.stereotype.Service;

import com.cinema.CineConnect.repository.PurchaseRepository;

@Service
public class TicketService {

    private final PurchaseRepository purchaseRepository;

    public TicketService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // Atualiza tickets/pedidos no banco de dados usando o sessionId do Stripe
    public void markTicketsAsPaid(String stripeSessionId) {
        // TODO: buscar pedidos pelo stripeSessionId no seu DB e marcar como pago
        System.out.println("Pagamento confirmado para sessão Stripe: " + stripeSessionId);
    }

    public void confirmPurchase(java.util.UUID purchaseId) {
        System.out.println("Confirming purchase: " + purchaseId);
        purchaseRepository.updateStatus(purchaseId, "PAID");
    }
}
