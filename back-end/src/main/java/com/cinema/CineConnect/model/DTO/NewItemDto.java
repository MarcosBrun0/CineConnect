package com.cinema.CineConnect.model.DTO;

import com.cinema.CineConnect.model.CartItem;

import java.util.List;
import java.util.UUID;

public record NewItemDto(
        UUID userId,
        List<UUID> addonIds,
        UUID productId,
        Integer quantity
){}


