package com.project.fmproject.domain.service;

import com.project.fmproject.domain.model.Documentos;
import com.project.fmproject.domain.repository.DocumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DocumentoService {
    @Autowired
    private DocumentosRepository documentosRepository;

    public Documentos atualizarDocumento(Long id, Documentos novoDocumento) {
        Documentos documentoExistente = documentosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Documento não encontrado"));

        // Atualize os campos do documento existente com os valores do novo documento
        documentoExistente.setTipo(novoDocumento.getTipo());
        // Atualize outros campos, se necessário

        return documentosRepository.save(documentoExistente);
    }
}
