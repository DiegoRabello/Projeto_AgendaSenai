package com.apisenai.agenda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventoDto {

    private Long id;
    @NotBlank(message = "Nome do contato obrigatório")
    private String nome;

}
