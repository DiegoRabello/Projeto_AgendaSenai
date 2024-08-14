package com.apisenai.agenda.classes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

@Entity
@EqualsAndHashCode(of = "id")
@Data
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Nome do contato obrigatório")
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email inválido")
    @NotBlank(message = "Email do contato obrigatório")
    private String email;
}
