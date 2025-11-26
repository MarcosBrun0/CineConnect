package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.service.MercadoPagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Using wildcard for brevity

import java.util.List;
import java.util.Map;

@RestController
public class MercadoPagoController {

    private final MercadoPagoService mercadoPagoService;

    // Recommendation: Use constructor injection (standard in Spring)
    public MercadoPagoController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @PostMapping("/api/createcart")
    public ResponseEntity<String> createPreference(@RequestBody List<ProductRecord> cart) {
        // Fix: Call service once and return the ID
        String preferenceId = mercadoPagoService.createPreference(cart);
        return ResponseEntity.ok(preferenceId);
    }

    @PostMapping("/api/handlePayments")
    public ResponseEntity<Map<String, String>> handlePayments(@RequestBody Map<String, String> payload) {
        // Assuming payload contains "email" and potentially "amount"
        String email = payload.get("email");
        // You should probably pass the amount here or calculate it based on an Order ID
        return ResponseEntity.ok(mercadoPagoService.createPayment(email));
    }
}