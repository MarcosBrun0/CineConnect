package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import com.cinema.CineConnect.model.DTO.PurchaseDetailsRecord;
import com.cinema.CineConnect.model.DTO.UserRecordRoleName;
import com.cinema.CineConnect.repository.AuthRepository;
import com.cinema.CineConnect.repository.PurchaseRepository;
import com.cinema.CineConnect.repository.UserRepository;
import com.cinema.CineConnect.service.JwtTokenService;
import com.cinema.CineConnect.service.PasswordVerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public List<PurchaseDetailsRecord> findMe(@AuthenticationPrincipal Jwt jwt) {
        UUID uuid = UUID.fromString(jwt.getSubject());
        System.out.println(purchaseRepository.retrieveUserPurchases(uuid));
        return purchaseRepository.retrieveUserPurchases(uuid);
    }
}
