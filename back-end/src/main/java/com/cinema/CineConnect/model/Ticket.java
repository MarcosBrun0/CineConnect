package com.cinema.CineConnect.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Ticket extends Product{
    UUID sessionId;
    public Ticket(UUID productId,UUID sessionId, String name, String type, BigDecimal price) {
        super(productId,name, type,price);
        this.sessionId=sessionId;

    }
}
