package com.cinema.CineConnect.model;

import java.time.LocalDate;

public class Admin extends User{

    public Admin( String name,String password, String email, String role, LocalDate birth_date) {
        super(name, password, email, role,    birth_date);
    }

    public String getInfo() {
        return "Hello user " + name + "! your email is " + email+", you are an admin!";
    }

}
