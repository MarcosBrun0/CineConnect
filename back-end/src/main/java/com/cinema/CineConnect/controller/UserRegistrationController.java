package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.RegistrationRequestRecord;
import com.cinema.CineConnect.model.DTO.UserRecordRoleId;
import com.cinema.CineConnect.repository.RoleRepository;
import com.cinema.CineConnect.repository.AuthRepository;
import com.cinema.CineConnect.repository.UserRepository;
import com.cinema.CineConnect.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserRegistrationController {

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RegistrationService registrationService;
    public UserRegistrationController(AuthRepository authRepository, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RegistrationService registrationService) {
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.registrationService = registrationService;
    }

    @Transactional
    @PostMapping("/api/register")   //Registro de Clientes
    public ResponseEntity<UserRecordRoleId> newUser(@RequestBody RegistrationRequestRecord registrationRequestRecord) {

        authRepository.findByEmail(registrationRequestRecord.email());
        if(authRepository.findByEmail(registrationRequestRecord.email()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"An user with this email already exists");
        }

        if(!roleRepository.findRoleID("Client").isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid role");
        }

        registrationService.createAndSaveUser(registrationRequestRecord);


        return ResponseEntity.ok().build();
    }


}
