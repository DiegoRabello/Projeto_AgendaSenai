package com.apisenai.agenda.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "evento_convite")
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "contato_id", referencedColumnName = "id")
    private Contato contato;
}