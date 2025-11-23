package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.MovieRecord;
import com.cinema.CineConnect.repository.MovieRepository;
import com.cinema.CineConnect.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieRecord> createMovie(
            @RequestPart("movie") MovieRecord movie, // O JSON
            @RequestPart("file") MultipartFile file // O Arquivo
    ) {
        try {
            MovieRecord savedMovie = movieService.saveMovieWithImage(movie, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<MovieRecord> getAllMovies() {
        return movieService.findAll();
    }
}
