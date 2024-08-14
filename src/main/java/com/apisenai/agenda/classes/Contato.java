package com.apisenai.agenda.classes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    
    private String email;
}
