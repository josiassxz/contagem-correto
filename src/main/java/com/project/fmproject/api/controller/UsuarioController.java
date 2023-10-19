package com.project.fmproject.api.controller;

import com.project.fmproject.domain.dto.UsuarioDTO;
import com.project.fmproject.domain.model.Usuario;
import com.project.fmproject.domain.repository.UsuarioRepository;
import com.project.fmproject.domain.service.UsuarioService;
import com.project.fmproject.domain.specification.UsuariosSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.listarUsuarios(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario usuario) {
        UsuarioDTO usuarioDTO = usuarioService.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioDTO != null) {
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/buscarEmail")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") String email) {
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarNome")
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam("nome") String nome) {
        Usuario usuario = usuarioService.findByNome(nome);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/buscar")
    // public ResponseEntity<Page<Usuario>> buscarPorNomeOuEmail(@RequestParam(value = "nome", required = false) String nome,
    //                                                           @RequestParam(value = "email", required = false) String email,
    //                                                           Pageable pageable) {
    //     Page<Usuario> usuarios = usuarioRepository.buscarPorNomeEmail(nome, email, pageable);
    //     if (!usuarios.isEmpty()) {
    //         return ResponseEntity.ok(usuarios);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @GetMapping("/pesquisar")
    public Page<Usuario> filtrarUsuarios(@RequestParam(value = "nome", required = false) String nome,
                                         @RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "tipo", required = false) String tipo,
                                         Pageable pageable ) {
        Specification<Usuario> specification = UsuariosSpecification.filtrarUsuarios(nome, email, tipo);
        return usuarioRepository.findAll(specification, pageable);
    }





}
