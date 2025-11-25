package com.cinema.CineConnect.model.DTO;
import  java.time.LocalDate;
public record EmployeeRecord(
    Integer id,
    String role,
    String name,
    String email,
    String password,
    LocalDate birthDate
){ }
