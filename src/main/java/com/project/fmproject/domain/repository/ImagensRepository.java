package com.project.fmproject.domain.repository;

import com.project.fmproject.domain.model.Imagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagensRepository extends JpaRepository<Imagens, Long>, JpaSpecificationExecutor<com.project.fmproject.domain.model.Imagens> {
    Imagens getById(Long id);

    List<Imagens> findAllByPostoContagem_Id(Long id);
}
