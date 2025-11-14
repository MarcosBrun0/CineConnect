package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.service.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MercadoPagoController {
    MercadoPagoService mercadoPagoService;

    MercadoPagoController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @GetMapping("/api/preferences")
    public String preferenceid() {
        System.out.println("preference"+mercadoPagoService.createPreference());
        return mercadoPagoService.createPreference();
    }

}