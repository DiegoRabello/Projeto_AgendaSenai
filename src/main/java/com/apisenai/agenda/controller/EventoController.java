package com.apisenai.agenda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apisenai.agenda.classes.Evento;
import com.apisenai.agenda.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RestControllerAdvice
@RequestMapping("evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Operation(summary = "Obtém todos os eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)))
    })
    @GetMapping
    public ResponseEntity<List<Evento>> getAll() {
        List<Evento> eventos = eventoService.getAll();
        return ResponseEntity.ok(eventos);
    }

    @Operation(summary = "Obtém um evento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getById(@PathVariable Long id) {
        Evento evento = eventoService.getById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evento);
    }

    @Operation(summary = "Cria um novo evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)))
    })
    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento evento) {
        Evento eventoSalvo = eventoService.create(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    @Operation(summary = "Atualiza um evento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
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

    @Operation(summary = "Exclui um evento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
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