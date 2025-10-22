package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Movie;
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
        public List<Movie> findAll() {
            return movieRepository.findAll();
        }


        @GetMapping("/{name}")
        public Optional<Movie> findByName(@PathVariable String name) {
            return movieRepository.findByName(name);

        }
}
