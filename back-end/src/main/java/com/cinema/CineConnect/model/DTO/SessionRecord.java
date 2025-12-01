package com.cinema.CineConnect.model.DTO;

import java.time.LocalDateTime;

public record SessionRecord(
        Integer sessionId,
        LocalDateTime timeDate,
        String language,
        Integer movieId,
        Integer theaterId
) {}
