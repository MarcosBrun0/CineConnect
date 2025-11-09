package com.cinema.CineConnect.model;

import java.time.LocalDate;

public class Cashier extends Employee{

    public Cashier(String name, String password, String email, String role, LocalDate birth_date) {
        super(name,password, email, role,   birth_date);
    }


    public String GetInfo() {
        return "Hello "+name+"! your email is "+email;
    }

}
