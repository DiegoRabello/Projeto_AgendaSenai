package com.apisenai.agenda.classes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contatos", referencedColumnName = "id")
    private List<Contato> convidados;

    @Column(name = "nome_do_evento", nullable = false)
    private String nome;

    @Column(name = "descricao_evento")
    private String descricao;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    private LocalDateTime dataHoraFim;

    public void addConvidado(Contato contato) {
        this.convidados.add(contato);
    }

    public void removeConvidado(Contato contato) {
        this.convidados.remove(contato);
    }
}
