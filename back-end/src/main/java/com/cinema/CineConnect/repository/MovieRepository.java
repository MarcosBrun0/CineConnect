package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.Movie;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    private final JdbcClient jdbcClient;

    public MovieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;

    }

    public List<Movie> findAll(){
        return jdbcClient.sql("SELECT * FROM public.movies")
                .query(Movie.class)
                .list();
    }
}
