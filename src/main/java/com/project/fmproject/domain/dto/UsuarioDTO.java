package com.project.fmproject.domain.dto;


import com.project.fmproject.domain.model.PostoContagem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private String tipo;
    private List<PostoContagem> empresas;
}