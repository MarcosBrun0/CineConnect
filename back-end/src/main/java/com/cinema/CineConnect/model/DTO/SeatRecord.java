package com.cinema.CineConnect.model.DTO;

public record SeatRecord(
        Integer seatId,
        Integer rowNum, // No banco está row_num
        Integer seatNum,
        Integer theaterId
) {}