package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.UserRecordRoleId;
import com.cinema.CineConnect.model.DTO.UserRecordRoleName;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;




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
//
//        @PreAuthorize("hasAnyAuthority()")
//        @GetMapping("/api/me")
//        public UserRecordRoleName findMe() {
//
//        }
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




