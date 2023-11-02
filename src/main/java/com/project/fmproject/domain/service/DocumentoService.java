package com.project.fmproject.domain.service;

import com.project.fmproject.domain.model.Documentos;
import com.project.fmproject.domain.model.PostoContagem;
import com.project.fmproject.domain.repository.DocumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Map<String, Map<String, List<String>>> getDocumentosStatisticsProcessed(Long postoId) {
        List<Object[]> resultados = documentosRepository.getDocumentosStatistics(postoId);
        Map<String, Map<String, List<String>>> resultadoFinal = new HashMap<>();

        for (Object[] resultado : resultados) {
            String hora = (String) resultado[0];
            String minuto = (String) resultado[1];
            Long totalTipos = (Long) resultado[2];
            String tipo = (String) resultado[3];

            resultadoFinal
                    .computeIfAbsent(hora, key -> new HashMap<>())
                    .computeIfAbsent(minuto, key -> new ArrayList<>())
                    .add(tipo);
        }

        return resultadoFinal;
    }
}




