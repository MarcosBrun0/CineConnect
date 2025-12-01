package com.cinema.CineConnect.service;

import org.springframework.stereotype.Service;

import com.cinema.CineConnect.repository.PurchaseRepository;

@Service
public class TicketService {

    private final PurchaseRepository purchaseRepository;
    private final com.cinema.CineConnect.repository.ProductRepository productRepository;

    public TicketService(PurchaseRepository purchaseRepository,
            com.cinema.CineConnect.repository.ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    // Atualiza tickets/pedidos no banco de dados usando o sessionId do Stripe
    public void markTicketsAsPaid(String stripeSessionId) {
        // TODO: buscar pedidos pelo stripeSessionId no seu DB e marcar como pago
        System.out.println("Pagamento confirmado para sessão Stripe: " + stripeSessionId);
    }

    public void confirmPurchase(java.util.UUID purchaseId) {
        System.out.println("Confirming purchase: " + purchaseId);
        purchaseRepository.updateStatus(purchaseId, "PAID");

        com.cinema.CineConnect.model.Purchase purchase = purchaseRepository.retrievePurchaseById(purchaseId);
        if (purchase != null) {
            for (com.cinema.CineConnect.model.PurchaseItem item : purchase.getItems()) {
                updateProductStock(item.getProductId(), item.getQuantity());

                if (item.getAddons() != null) {
                    for (com.cinema.CineConnect.model.PurchaseItem addon : item.getAddons()) {
                        updateProductStock(addon.getProductId(), addon.getQuantity());
                    }
                }
            }
        }
    }

    private void updateProductStock(java.util.UUID productId, int quantityPurchased) {
        Integer currentQuantity = productRepository.getQuantityById(productId);
        if (currentQuantity != null) {
            int newQuantity = currentQuantity - quantityPurchased;
            if (newQuantity < 0)
                newQuantity = 0; // Prevent negative stock
            productRepository.updateProductQuantity(productId, newQuantity);
        }
    }

}
