package com.project.fmproject.domain.repository;

import com.project.fmproject.domain.model.PostoContagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PostoContagemRepository extends JpaRepository<PostoContagem, Long>, JpaSpecificationExecutor<PostoContagem> {

    List<PostoContagem> findByNomeContainingIgnoreCase(String nome);


    Optional<PostoContagem> findByCnpj(String cnpj);


}
