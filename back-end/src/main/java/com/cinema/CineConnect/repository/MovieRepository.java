package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Movie;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class MovieRepository {

    private final JdbcClient jdbcClient;

    public MovieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;

    }

    public List<Movie> FindAll(){
        return jdbcClient.sql("SELECT movie_id, name, duration,rating,img FROM movies")
                .query(Movie.class)
                .list();
    }
}
