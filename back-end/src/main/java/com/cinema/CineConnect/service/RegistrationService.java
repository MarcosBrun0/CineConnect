package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.RegistrationRequestRecord;
import com.cinema.CineConnect.model.DTO.UserRecord;
import com.cinema.CineConnect.repository.AuthRepository;
import com.cinema.CineConnect.repository.RoleRepository;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(AuthRepository authRepository, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createAndSaveUser(RegistrationRequestRecord registrationRequestRecord) {
        var role = roleRepository.findRoleID("Client");
        UserRecord user = new UserRecord(registrationRequestRecord.name(),
                registrationRequestRecord.email(), bCryptPasswordEncoder.encode(registrationRequestRecord.password()),
                role.get(),
                registrationRequestRecord.birthDate());

        userRepository.saveUser(user);
    }
}
