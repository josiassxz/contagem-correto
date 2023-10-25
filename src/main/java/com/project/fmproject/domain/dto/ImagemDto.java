package com.project.fmproject.domain.dto;

import com.project.fmproject.domain.model.Imagens;
import com.project.fmproject.domain.model.PostoContagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemDto {
    private String base64Content;
    private String nome;
    private String tipo;
    private PostoContagem posto;
    private Imagens imagem;
    // Outros campos que você deseja incluir


}
