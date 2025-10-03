package com.cinema.CineConnect;

import com.cinema.CineConnect.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CineConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CineConnectApplication.class, args);
    }
}

