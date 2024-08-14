package com.apisenai.agenda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apisenai.agenda.classes.Evento;
import com.apisenai.agenda.service.EventoService;

@RestController
@RequestMapping("evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> getAll() {
        List<Evento> eventos = eventoService.getAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getById(@PathVariable Long id) {
        Evento evento = eventoService.getById(id);

        if (evento == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento evento) {
        Evento eventoSalvo = eventoService.create(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody Evento evento) {
        Evento eventoExistente = eventoService.getById(id);
        if (eventoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        eventoExistente.setNome(evento.getNome());
        eventoExistente.setDescricao(evento.getDescricao());
        eventoExistente.setDataHoraInicio(evento.getDataHoraInicio());
        eventoExistente.setDataHoraFim(evento.getDataHoraFim());

        Evento eventoSalvo = eventoService.update(id, eventoExistente);
        return ResponseEntity.ok(eventoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Evento eventoExistente = eventoService.getById(id);
        if (eventoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}