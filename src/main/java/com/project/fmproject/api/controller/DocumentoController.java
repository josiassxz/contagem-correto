package com.project.fmproject.api.controller;

import com.project.fmproject.domain.model.Documentos;
import com.project.fmproject.domain.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PutMapping("/{id}")
    public ResponseEntity<Documentos> atualizarDocumento(@PathVariable Long id, @RequestBody Documentos novoDocumento) {
        Documentos documentoAtualizado = documentoService.atualizarDocumento(id, novoDocumento);
        return ResponseEntity.ok(documentoAtualizado);
    }
}

