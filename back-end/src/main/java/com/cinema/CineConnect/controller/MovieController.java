package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Movie;
import com.cinema.CineConnect.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
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
