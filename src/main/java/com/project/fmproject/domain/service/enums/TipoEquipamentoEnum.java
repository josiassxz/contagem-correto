package com.project.fmproject.domain.service.enums;

public enum TipoEquipamentoEnum {

    VASO( "VASO", "Vaso de pressão"),
    VALVULA( "VALVULA", "Válvula de segurança"),
    INDICADOR( "INDICADOR", "Indicador de pressão"),
    CALDEIRA( "CALDEIRA", "Caldeira"),
    LINHA( "LINHA", "Linha de vida"),
    ELEVADOR( "ELEVADOR", "Elevador"),
    INDICADOR_TEMPERATURA("INDICADOR_TEMPERATURA", "Indicador de temperatura"),
    EQUIPAMENTO( "EQUIPAMENTO", "Equipamento"),
    TUBULACAO( "TUBULACAO", "Tubulação");


    TipoEquipamentoEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private final String codigo;
    private final String descricao;

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}