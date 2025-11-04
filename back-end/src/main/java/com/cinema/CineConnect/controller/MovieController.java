package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.MovieRecord;
import com.cinema.CineConnect.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

        @GetMapping
        public List<MovieRecord> findAll() {
            return movieRepository.findAll();
        }


        @GetMapping("/{name}")
        public Optional<MovieRecord> findByName(@PathVariable String name) {
            return movieRepository.findByName(name);

        }
}
