package com.apisenai.agenda.dto;

import lombok.Data;

@Data
public class ContatoUpdateDto {

    private Long id;
    private String nome;
    private String email;
}