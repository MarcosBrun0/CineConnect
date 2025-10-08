package com.cinema.CineConnect.model;// src/main/java/com/cinema/CineConnect/model/Movie.java

import java.math.BigDecimal;

public record Movie(
        Integer movieId,
        String name,
        Integer duration,
        BigDecimal rating, // Was Integer, should be BigDecimal or Double for decimals
        String img
) {
}