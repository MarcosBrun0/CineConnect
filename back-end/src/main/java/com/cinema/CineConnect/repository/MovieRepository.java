package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.MovieRecord;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final JdbcClient jdbcClient;

    public MovieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;

    }

    public List<MovieRecord> findAll(){
        return jdbcClient.sql("SELECT * FROM public.movies")
                .query(MovieRecord.class)
                .list();
    }
    public Optional<MovieRecord> findByName(String name){
        return jdbcClient.sql("SELECT * FROM public.movies WHERE name = :name").param("name",name).query(MovieRecord.class).optional();

    }
}
