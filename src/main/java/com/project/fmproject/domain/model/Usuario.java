package com.project.fmproject.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nome;

    private String tipo;

    private String senha;


    @ManyToMany(mappedBy = "usuarios")
    private List<PostoContagem> posto;
}


