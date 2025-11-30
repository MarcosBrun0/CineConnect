package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.service.StripeService;
import com.cinema.CineConnect.service.TicketService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    private final StripeService stripeService;
    private final TicketService ticketService;

    @Value("${STRIPE_WEBHOOK_SECRET}")
    private String endpointSecret;

    public StripeController(StripeService stripeService, TicketService ticketService) {
        this.stripeService = stripeService;
        this.ticketService = ticketService;
    }

    //Checkout Session
    @PostMapping("/create-session")
    public ResponseEntity<String> createCheckoutSession(
            @RequestBody List<ProductRecord> cart,
            @RequestParam String successUrl,
            @RequestParam String cancelUrl) {

        String checkoutUrl = stripeService.createCheckoutSession(cart, successUrl, cancelUrl);
        return ResponseEntity.ok(checkoutUrl);
    }


    //Receives successful transactions
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook signature verification failed");
        }

        switch (event.getType()) {
            case "checkout.session.completed":
                Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
                if (session != null) {
                    String sessionId = session.getId();
                    ticketService.markTicketsAsPaid(sessionId);
                }
                break;
            case "payment_intent.payment_failed":
                // Log ou notificação de falha
                break;
            default:
                break;
        }

        return ResponseEntity.ok("");
    }
}
