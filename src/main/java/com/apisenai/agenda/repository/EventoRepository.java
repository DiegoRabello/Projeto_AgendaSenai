package com.apisenai.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apisenai.agenda.classes.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
