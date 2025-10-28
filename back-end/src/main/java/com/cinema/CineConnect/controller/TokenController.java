//package com.cinema.CineConnect.controller;
//
//import com.cinema.CineConnect.model.DTO.LoginRequestRecord;
//import com.cinema.CineConnect.model.DTO.LoginResponseRecord;
//import com.cinema.CineConnect.repository.AuthRepository;
//import com.cinema.CineConnect.repository.UserRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TokenController {
//
//    private final JwtEncoder jwtEncoder;
//    private final AuthRepository authRepository;
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public TokenController(JwtEncoder jwtEncoder, AuthRepository authRepository, UserRepository userRepository) {
//        this.jwtEncoder = jwtEncoder;
//        this.authRepository = authRepository;
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<LoginResponseRecord> login(@RequestBody LoginRequestRecord loginRequestRecord) {
////
////        var user = authRepository.findByEmail(loginRequestRecord.email());
////
////        if(user.isEmpty() || ){
////            throw new BadCredentialsException("Invalid email or password");
////        }
////
////    }
////
//
//
//
//}
