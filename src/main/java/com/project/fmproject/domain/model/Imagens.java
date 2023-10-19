package com.project.fmproject.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Imagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;


    @ManyToOne
    @JsonIgnoreProperties("posto")
    @JoinColumn(name = "posto_id")
    private PostoContagem posto;

    @JsonIgnoreProperties("imagem")
    @OneToMany(mappedBy = "imagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documentos> documentos = new ArrayList<>();

    @JsonIgnore
    @ElementCollection
    private List<String> caminhosDocumentos = new ArrayList<>();

    public void adicionarDocumento(Documentos documento, String caminho) {
        this.documentos.add(documento);
        this.caminhosDocumentos.add(caminho);
        documento.setImagem(this);
    }



    public void removeDocumento(Documentos documento) {
        this.documentos.remove(documento);
        this.caminhosDocumentos.remove(documento.getCaminho());
        documento.setImagem(this);
    }
}



