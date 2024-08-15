package com.apisenai.agenda.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apisenai.agenda.classes.Convite;
import com.apisenai.agenda.classes.Evento;

public interface ConviteRepository extends JpaRepository<Convite, Long> {
    Optional<List<Convite>> findByEvento(Evento evento);
}