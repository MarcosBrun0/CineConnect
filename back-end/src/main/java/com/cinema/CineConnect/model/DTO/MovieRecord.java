package com.cinema.CineConnect.model.DTO;// src/main/java/com/cinema/CineConnect/model/MovieRecord.java

import java.math.BigDecimal;

public record MovieRecord(
        Integer movieId,
        String name,
        Integer duration,
        BigDecimal rating, // Was Integer, should be BigDecimal or Double for decimals
        String img
) {
}