package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.UserRecordRoleName;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
    @RequestMapping
    public class UserController {

        private final UserRepository userRepository;

        public UserController(UserRepository userRepository) {

            this.userRepository = userRepository;
        }

        // GET /api/usuarios
        @PreAuthorize("hasAuthority('SCOPE_Admin')")
        @GetMapping("/api/usuarios")
        public List<UserRecordRoleName> findAll() {
            return userRepository.findAllUsersRoleName();

        }

        @PreAuthorize("isAuthenticated()")
        //Any user can make a request,as long as they are logged in
        @GetMapping("/api/me")
        public Optional<UserRecordRoleName> findMe(@AuthenticationPrincipal Jwt jwt) {
            UUID uuid = UUID.fromString(jwt.getSubject());
            return userRepository.findInfoById(uuid);
        }
    }


        //@GetMapping("/api/user/profile")


//
//        // GET /api/usuarios/{id}
//        @GetMapping("/{id}")
//        public ResponseEntity<UserRecordRoleId> findById(@PathVariable Integer id) {
//            return userRepository.findById(id)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//        }




