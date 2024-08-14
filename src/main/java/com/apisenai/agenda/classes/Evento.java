package com.apisenai.agenda.classes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contato_id", referencedColumnName = "id")
    private Contato contato;

    @Column(name = "nome_do_evento", nullable = false)
    @NotBlank(message = "Nome do Evento é obrigatório")
    private String nome;

    @Column(name = "descricao_evento")
    private String descricao;

    @Column(name = "data_hora_inicio")
    @NotNull(message = "A data e hora de início são obrigatórias")
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    @NotNull(message = "A data e hora de fim são obrigatórias")
    private LocalDateTime dataHoraFim;

    @Column(name = "evento_ativo")
    private boolean eventoAtivo = true;
}
