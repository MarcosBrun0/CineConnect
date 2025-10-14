package com.cinema.CineConnect.model;
import  java.time.LocalDate;
public record Employee(
    Integer id,
    String role,
    String name,
    String email,
    String password,
    LocalDate birthDate
){ }
