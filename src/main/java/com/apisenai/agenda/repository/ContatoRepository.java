package com.apisenai.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apisenai.agenda.classes.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
