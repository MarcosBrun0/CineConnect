package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.Usuario;
import com.cinema.CineConnect.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserController {




    @RestController
    @RequestMapping("/api/usuarios")
    public class UsuarioController {

        private final UsuarioRepository usuarioRepository;

        public UsuarioController(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        // GET /api/usuarios
        @GetMapping
        public List<Usuario> findAll() {
            return usuarioRepository.findAll();
        }

        // GET /api/usuarios/{id}
        @GetMapping("/{id}")
        public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
            return usuarioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }


    }
}
