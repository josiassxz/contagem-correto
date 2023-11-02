package com.project.fmproject.api.controller;

import com.project.fmproject.domain.dto.DocumentoStatisticsDTO;
import com.project.fmproject.domain.model.Documentos;
import com.project.fmproject.domain.repository.DocumentosRepository;
import com.project.fmproject.domain.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DocumentosRepository documentosRepository;

    @PutMapping("/{id}")
    public ResponseEntity<Documentos> atualizarDocumento(@PathVariable Long id, @RequestBody Documentos novoDocumento) {
        Documentos documentoAtualizado = documentoService.atualizarDocumento(id, novoDocumento);
        return ResponseEntity.ok(documentoAtualizado);
    }

    @GetMapping("/relatorios/{postoId}")
    public ResponseEntity<Map<String, Map<String, List<String>>> > getDocumentosStatistics(@PathVariable Long postoId) {
        Map<String, Map<String, List<String>>> resultadoFinal = documentoService.getDocumentosStatisticsProcessed(postoId);
        return ResponseEntity.ok(resultadoFinal);
    }



}

