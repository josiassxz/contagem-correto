package com.project.fmproject.domain.service.enums;

public enum TipoDocumentoEnum {
    RELATORIO(1, "Relatório de Inspeção"),
    LIVRO(2, "Livro de Registro"),
    PRONTUARIO(3, "Prontuário"),
    PROJETO(4, "Projeto de Instalação"),
    MANUAL(5, "Manual de Operação"),
    CERTIFICADO(6, "Certificado de Calibração");

     TipoDocumentoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private final int codigo;
    private final String descricao;

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}

