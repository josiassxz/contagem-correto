package com.project.fmproject.domain.repository;

import com.project.fmproject.domain.dto.DocumentoStatisticsDTO;
import com.project.fmproject.domain.model.Documentos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentosRepository extends JpaRepository<Documentos, Long> {

    @Query("SELECT doc, img " +
            "FROM Documentos doc " +
            "INNER JOIN doc.imagem img " +
            "WHERE doc.tipo IS NULL AND img.posto.id = :postoId " +
            "ORDER BY doc.id ASC")
    Page<Object[]> findDocumentsAndImagesByPostoIdAndTipoIsNullOrderByidAsc(@Param("postoId") Long postoId, Pageable pageable);




    @Query("SELECT " +
            "    d.hora, " +
            "    d.minuto, " +
            "    COUNT(DISTINCT d.tipo) AS total_tipos, " +
            "    d.tipo AS tipos " +
            "FROM Documentos d " +
            "JOIN d.imagem i " +
            "WHERE i.posto.id = :postoId " +
            "GROUP BY d.hora, d.minuto, d.tipo")
    List<Object[]> getDocumentosStatistics(@Param("postoId") Long postoId);






}
