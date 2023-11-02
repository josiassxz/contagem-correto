package com.project.fmproject.domain.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoStatisticsDTO {
    private int hora;
    private int minuto;
    private long totalTipos;
    private String tipos;

}
