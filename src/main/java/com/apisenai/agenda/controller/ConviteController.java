package com.apisenai.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apisenai.agenda.classes.Convite;
import com.apisenai.agenda.service.ConviteService;
import com.apisenai.agenda.classes.Evento;
import com.apisenai.agenda.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RestControllerAdvice
@RequestMapping("convite")
public class ConviteController {

    @Autowired
    private ConviteService conviteService;

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Buscar todos os convites", description = "Retorna uma lista com todos os convites ")
    @ApiResponse(responseCode = "200", description = "Convites Encontrados")
    @ApiResponse(responseCode = "404", description = "Convites não Encontrados, Tente Novamente!")
    @GetMapping
    public ResponseEntity<List<Convite>> getAll() {
        List<Convite> convites = conviteService.getAll();
        return ResponseEntity.ok(convites);
    }

    @Operation(summary = "Buscar todos os convites de um evento", description = "Retorna uma lista com todos os convites associados a un evento, pela id do evento")
    @ApiResponse(responseCode = "200", description = "Convites Encontrados")
    @ApiResponse(responseCode = "404", description = "Convites não Encontrado, Tente Novamente!")
    @GetMapping("/evento/{id}")
    public ResponseEntity<List<Convite>> getByEvento(@PathVariable Long id) {
        Evento evento = eventoService.getById(id);
        List<Convite> convites = conviteService.getByEvento(evento);
        return ResponseEntity.ok(convites);
    }

    @Operation(summary = "Buscar convite por id", description = "Retorna um convite por id ")
    @ApiResponse(responseCode = "200", description = "Convite Encontrado")
    @ApiResponse(responseCode = "404", description = "Convite não Encontrado, Tente Novamente!")
    @GetMapping("/{id}")
    public ResponseEntity<Convite> getById(@PathVariable Long id) {
       return ResponseEntity.ok(conviteService.getById(id));
    }
        
    @Operation(summary = "Cadastrar um novo convite", description = "Cadastrar um novo convite")
    @ApiResponse(responseCode = "200", description = "Convite cadastrado")
    @ApiResponse(responseCode = "404", description = "Convite não cadastrado, Tente Novamente!")
    @PostMapping
    public ResponseEntity<Convite> create(@RequestBody Convite convite) {
        Convite novoConvite = conviteService.create(convite);
        return ResponseEntity.ok(novoConvite);
    }

    @Operation (summary = "Atualizar Convite por id", description = "Atualiza um Convite por id")
    @ApiResponse(responseCode = "200", description = "Convite atualizado")
    @ApiResponse(responseCode = "404", description = "Convite não atualizado, Tente Novamente!")
    @PutMapping("/{id}")
    public ResponseEntity<Convite> update(@PathVariable Long id, @RequestBody Convite convite) {

        Convite conviteUpdate = conviteService.getById(id);

        if (conviteUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        if (conviteUpdate.getEvento() != null) {
            conviteUpdate.setEvento(convite.getEvento());
        }

        if (conviteUpdate.getContato() != null) {
            conviteUpdate.setContato(convite.getContato());
        }

        conviteService.create(conviteUpdate);
        return ResponseEntity.ok(conviteService.getById(id));
    }

    @Operation(summary = "Deletar um convite por id", description = "Deleta um convite por id")
    @ApiResponse(responseCode = "200", description = "Convite deletado")
    @ApiResponse(responseCode = "404", description = "Convite não deletado, Tente Novamente!")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Convite convite = conviteService.getById(id);
        if (convite == null) {
            return ResponseEntity.notFound().build();
        }
        conviteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
