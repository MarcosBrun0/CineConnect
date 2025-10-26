package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.UserRecord;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;




    @RestController
    @RequestMapping("/api/usuarios")
    public class UserController {

        private final UserRepository userRepository;

        public UserController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        // GET /api/usuarios
        @GetMapping
        public List<UserRecord> findAll() {
            return userRepository.findAll();
        }
//
//        // GET /api/usuarios/{id}
//        @GetMapping("/{id}")
//        public ResponseEntity<UserRecord> findById(@PathVariable Integer id) {
//            return userRepository.findById(id)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//        }


    }
