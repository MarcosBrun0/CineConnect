package com.cinema.CineConnect.controller;
import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
import com.cinema.CineConnect.model.DTO.LoginResponseRecord;
import com.cinema.CineConnect.repository.AuthRepository;
import com.cinema.CineConnect.repository.UserRepository;
import com.cinema.CineConnect.service.JwtTokenService;
import com.cinema.CineConnect.service.PasswordVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TokenController {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordVerificationService passwordVerificationService;
    private final JwtTokenService jwtTokenService;

    public TokenController(AuthRepository authRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PasswordVerificationService passwordVerificationService, JwtTokenService jwtTokenService ) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordVerificationService = passwordVerificationService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponseRecord> login(@RequestBody LoginRequestRecord loginRequestRecord) {

        // var user = optional<user>

        //user.get gets the value from the optional<user>
        if(!passwordVerificationService.verify(loginRequestRecord)){
            throw new BadCredentialsException("Invalid email or password");
        }

        var expiresIn = 360L;
        var jwtValue= jwtTokenService.generate(loginRequestRecord,expiresIn);

        return ResponseEntity.ok(new LoginResponseRecord(jwtValue,expiresIn));

    }




}
