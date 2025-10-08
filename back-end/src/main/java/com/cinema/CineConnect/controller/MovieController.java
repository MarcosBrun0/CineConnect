package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Movie;
import com.cinema.CineConnect.model.Usuario;
import com.cinema.CineConnect.repository.MovieRepository;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
