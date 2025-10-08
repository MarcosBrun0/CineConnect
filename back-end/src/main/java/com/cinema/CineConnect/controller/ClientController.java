package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Client;
import com.cinema.CineConnect.model.Movie;
import com.cinema.CineConnect.repository.ClientRepository;
import com.cinema.CineConnect.repository.MovieRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
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
    public List<Client> findAll() {
        return clientRepository.findAll();
    }


}
