package com.apisenai.agenda.dto;

import lombok.Data;

@Data
public class EventoUpdateDto {

    private Long id;
    private String nome;
    private String email;
}