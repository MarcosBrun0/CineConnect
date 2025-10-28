package com.cinema.CineConnect.model;

public abstract class User {
    String username;
    String password;
    String email;
    String Name;

    User(String username, String password, String email, String Name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.Name = Name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {

    }



}
