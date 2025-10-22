package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("authenticate")
    public String authenticate(){
        return authenticationService.authenticate();
    }
}
