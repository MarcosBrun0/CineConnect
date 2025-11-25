package com.cinema.CineConnect.model;

import java.time.LocalDate;

public class Manager extends Employee{

    public Manager( String name,String password, String email, String role, LocalDate birth_date) {
        super(name,password, email, role,    birth_date);
    }

    @Override
    public String GetInfo() {
        return ("Hello "+name+"! your email is "+email+" you are a manager!");
    }

}
