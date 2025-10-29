package com.cinema.CineConnect.model;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class User {
    protected String password;
    protected String role;
    protected String email;
    protected String name;

    public User(String name, String password, String email, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
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
