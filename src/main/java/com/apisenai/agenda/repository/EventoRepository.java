package com.apisenai.agenda.repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apisenai.agenda.classes.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query(value = "SELECT * FROM eventos WHERE :dataHora BETWEEN data_hora_inicio AND data_hora_fim", nativeQuery = true)
    Optional<Evento> findByDataHora(@Param("dataHora") LocalDateTime dataHora);
    
    Optional<List<Evento>> findByDataHoraFimLessThanEqual(LocalDateTime dataHora);

    Optional<List<Evento>> findByDataHoraInicioGreaterThanEqual(LocalDateTime dataHora);
}
