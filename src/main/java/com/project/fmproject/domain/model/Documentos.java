package com.project.fmproject.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caminho;
    private String nome;
    private String tipo;
    @Lob
    private byte[] arquivo;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    @JoinColumn(name = "imagem_id")
    private Imagens imagem;


}

