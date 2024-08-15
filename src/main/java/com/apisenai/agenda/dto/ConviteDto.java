package com.apisenai.agenda.dto;

public class ConviteDto {

    private Long id;
    private String descricao;
    private Long convidadoId;
    private String dataEvento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getConvidadoId() {
        return convidadoId;
    }

    public void setConvidadoId(Long convidadoId) {
        this.convidadoId = convidadoId;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
}
