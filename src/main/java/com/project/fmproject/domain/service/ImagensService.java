package com.project.fmproject.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.fmproject.domain.model.Documentos;
import com.project.fmproject.domain.model.Imagens;
import com.project.fmproject.domain.repository.DocumentosRepository;
import com.project.fmproject.domain.repository.ImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ImagensService {


    @Autowired
    private DocumentosRepository documentosRepository;


    @Autowired
    private ImagensRepository imagensRepository;

    public Page<Imagens> findAll(Pageable pageable) {
        return imagensRepository.findAll(pageable);
    }


    public Optional<Imagens> findById(Long id) {
        return imagensRepository.findById(id);
    }


    public Imagens salvar(String equipamentosJson, List<MultipartFile> files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Imagens equipamento = mapper.readValue(equipamentosJson, Imagens.class);

        // Obter o diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Configurar a relação bidirecional entre Equipamento e Documento
        List<Documentos> documentos = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String caminho = diretorioAtual + File.separator + UUID.randomUUID().getLeastSignificantBits() + " - " + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(caminho);
            Files.write(path, bytes);
            Documentos documento = new Documentos();
            documento.setCaminho(caminho);
            documento.setNome(equipamento.getDocumentos().get(i).getNome());
            documento.setTipo(equipamento.getDocumentos().get(i).getTipo());
            documento.setImagem(equipamento); // Estabelecer a relação com a imagem

            documentos.add(documento);
        }

        equipamento.setDocumentos(documentos);

        return imagensRepository.save(equipamento);
    }






    public Imagens alterar(Long equipamentoId, String equipamentosJson, List<MultipartFile> files) throws IOException {
        String diretorioAtual = System.getProperty("user.dir");
        Imagens imagemExistente = imagensRepository.findById(equipamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));


        ObjectMapper mapper = new ObjectMapper();
        Imagens equipamentoAtualizado = mapper.readValue(equipamentosJson, Imagens.class);
        imagemExistente.setDescricao(equipamentoAtualizado.getDescricao());



        if (equipamentoAtualizado.getDocumentos() != null && !equipamentoAtualizado.getDocumentos().isEmpty()) {
            int numDocumentos = Math.min(equipamentoAtualizado.getDocumentos().size(), files.size());


            Iterator<Documentos> documentoIterator = imagemExistente.getDocumentos().iterator();
            while (documentoIterator.hasNext()) {
                Documentos documentoExistente = documentoIterator.next();

                boolean documentoEncontrado = false;
                for (int i = 0; i < numDocumentos; i++) {
                    Documentos documentoAtualizado = equipamentoAtualizado.getDocumentos().get(i);
                    if (documentoExistente.getId() != null && documentoExistente.getId().equals(documentoAtualizado.getId())) {
                        documentoEncontrado = true;

                        if (!documentoExistente.getTipo().equals(documentoAtualizado.getTipo())) {
                            documentoExistente.setTipo(documentoAtualizado.getTipo());
                        }

                        equipamentoAtualizado.getDocumentos().remove(i);
                        numDocumentos--;
                        break;
                    }
                }

                if (!documentoEncontrado) {
                    documentoIterator.remove();
                }
            }

            equipamentoAtualizado.getDocumentos().forEach(doc -> {
                if (doc.getId() == null){
                    files.forEach(file -> {
                        if (file.getOriginalFilename().trim().equalsIgnoreCase(doc.getNome())){


                            if (!file.isEmpty()) {
                                String caminho = null;
                                caminho = diretorioAtual + File.separator + UUID.randomUUID().getLeastSignificantBits() + " - " + file.getOriginalFilename() ;
                                byte[] bytes = new byte[0];
                                try {
                                    bytes = file.getBytes();
                                    Path path = Paths.get(caminho);
                                    Files.write(path, bytes);
                                    Documentos documento = new Documentos();
                                    documento.setCaminho(caminho);
                                    documento.setNome(doc.getNome());
                                    documento.setTipo(doc.getTipo());
                                    imagemExistente.adicionarDocumento(documento, caminho);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    });
                }else {
                    documentosRepository.save(doc);
                }
            });


        } else {
            imagemExistente.getDocumentos().clear();
        }

        return imagensRepository.save(imagemExistente);

    }


    public void removerEquipamento(Long id) {
        imagensRepository.deleteById(id);
    }


}



