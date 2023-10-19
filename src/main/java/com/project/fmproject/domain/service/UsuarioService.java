package com.project.fmproject.domain.service;


import com.project.fmproject.domain.dto.UsuarioDTO;
import com.project.fmproject.domain.exception.EntidadeNaoEncontradaException;
import com.project.fmproject.domain.model.Usuario;
import com.project.fmproject.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmailContaining(email);
    }

    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNomeContaining(nome);
    }



    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarUsuarioPorId(id);
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setTipo(usuarioAtualizado.getTipo());
        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO findByEmailAndSenha(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setTipo(usuario.getTipo());
            usuarioDTO.setEmpresas(usuario.getPosto());
            return usuarioDTO;
        } else {
            return null;
        }
    }

}
