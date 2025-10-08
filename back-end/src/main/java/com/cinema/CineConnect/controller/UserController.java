package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Usuario;
import com.cinema.CineConnect.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        public List<Usuario> findAll() {
            return userRepository.findAll();
        }
//
//        //    GET /api/usuarios/{id}
//        @GetMapping("/{id}")
//        public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
//            return userRepository.findById(id)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//        }


    }
