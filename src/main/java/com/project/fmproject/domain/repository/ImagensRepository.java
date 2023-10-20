package com.project.fmproject.domain.repository;

import com.project.fmproject.domain.model.Imagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagensRepository extends JpaRepository<Imagens, Long>, JpaSpecificationExecutor<Imagens> {
    Imagens getById(Long id);

//    List<Imagens> findAllByPostoContagem_Id(Long id);

    @Query("select i from Imagens i where i.posto.id = ?1")
    List<Imagens> findAllByPosto_Id(Long id);
}
