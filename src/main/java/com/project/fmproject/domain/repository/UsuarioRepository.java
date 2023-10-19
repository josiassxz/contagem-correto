package com.project.fmproject.domain.repository;

import com.project.fmproject.domain.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {


    Usuario findByEmailAndSenha(String email, String senha);

    Usuario findByEmailContaining(String email);

    Usuario findByNomeContaining(String nome);

    //public Page<Usuario> buscarPorNomeEmail(String nome, String email, Pageable pageable);






}
