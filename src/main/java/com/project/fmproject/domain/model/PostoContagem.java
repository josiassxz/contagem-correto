package com.project.fmproject.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PostoContagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    private String km;
    private String sentido;
    private String dataContagem;

    @JsonIgnoreProperties("posto")
    @ManyToMany
    @JoinTable(name = "posto_usuario",
            joinColumns = @JoinColumn(name = "posto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios;

    @Transient
    private List<Long> idsUsuarios;

}
