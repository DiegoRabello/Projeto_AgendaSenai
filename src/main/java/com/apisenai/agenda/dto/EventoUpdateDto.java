package com.apisenai.agenda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventoUpdateDto {

    private Long id;
    @NotBlank(message = "Nome do contato obrigatório")
    private String nome;
    @NotBlank(message = "Email obrigatório")
    private String email;
}