package com.cinema.CineConnect.model;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public abstract class User {
    protected String password;
    protected String role;
    protected String email;
    protected String name;
    protected LocalDate birth_date;

    public User(String name, String password, String email, String role, LocalDate birth_date) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.birth_date = birth_date;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
     return name;
    }
    public LocalDate getBirth_date() {return birth_date;}
    public String getRole(){
        return role;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }


}
