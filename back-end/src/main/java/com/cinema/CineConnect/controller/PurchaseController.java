package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Purchase;
import com.cinema.CineConnect.repository.PurchaseRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/me/purchases")
    public List<Purchase> getPurchases(@AuthenticationPrincipal Jwt jwt) {
        UUID uuid = UUID.fromString(jwt.getSubject());
        System.out.println(purchaseRepository.retrieveUserPurchases(uuid));
        return purchaseRepository.retrieveUserPurchases(uuid);
    }
}
