package com.apisenai.agenda.dto;

import org.springframework.boot.context.properties.bind.Name;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContatoDto {
    
    private Long id;
    @NotBlank(message = "Nome do contato obrigatório")
    private String nome;

}