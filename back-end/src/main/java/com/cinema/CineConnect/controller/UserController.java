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

        // POST /api/usuarios
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Usuario create(@RequestBody Usuario usuario) {
            // Cria um novo usuário, o ID será gerado
            Usuario usuarioParaSalvar = new Usuario(null, usuario.nome(), usuario.email());
            return usuarioRepository.save(usuarioParaSalvar);
        }

        // PUT /api/usuarios/{id}
        @PutMapping("/{id}")
        public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
            // Verifica se o usuário existe antes de tentar atualizar
            if (usuarioRepository.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Cria o objeto Usuario com o ID da URI para atualização
            Usuario usuarioParaAtualizar = new Usuario(id, usuario.nome(), usuario.email());
            int linhasAfetadas = usuarioRepository.update(usuarioParaAtualizar);

            if (linhasAfetadas == 1) {
                return ResponseEntity.ok(usuarioParaAtualizar);
            } else {
                return ResponseEntity.internalServerError().build(); // Ou outro erro, se necessário
            }
        }

        // DELETE /api/usuarios/{id}
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
            int linhasAfetadas = usuarioRepository.deleteById(id);

            if (linhasAfetadas == 1) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
