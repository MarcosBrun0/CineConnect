package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.ClientRecord;
import com.cinema.CineConnect.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientRepository clientRepository;

    ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @GetMapping
    public List<ClientRecord> findAll() {
        return clientRepository.findAll();
    }



}
