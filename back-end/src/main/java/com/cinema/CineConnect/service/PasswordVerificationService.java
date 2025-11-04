package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import com.cinema.CineConnect.repository.AuthRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordVerificationService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordVerificationService(BCryptPasswordEncoder bCryptPasswordEncoder, AuthRepository authRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authRepository = authRepository;
    }

    public boolean verify(LoginRequestRecord loginRequestRecord) {
        var dbUser = authRepository.findByEmail(loginRequestRecord.email());

        if(dbUser.isEmpty()){
            return false;
        }

        var password = dbUser.get().password();

        //returns true if the decrypted password what is stored in the database
        return bCryptPasswordEncoder.matches(loginRequestRecord.password(), password);


    }



}
