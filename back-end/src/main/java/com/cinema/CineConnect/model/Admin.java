package com.cinema.CineConnect.model;

public class Admin extends User{

    public Admin( String name,String password, String email, String role){
        super(name, password, email, role);
    }

    public String GetInfoClient() {
        return "Hello user " + name + "! your email is " + email+", you are an administrator!";
    }

}
