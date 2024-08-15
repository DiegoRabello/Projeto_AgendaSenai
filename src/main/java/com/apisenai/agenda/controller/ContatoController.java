package com.apisenai.agenda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.apisenai.agenda.classes.Contato;
import com.apisenai.agenda.service.ContatoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController

@RequestMapping("contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Operation(summary = "Buscar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados ")
    @ApiResponse(responseCode = "200", description = "Clientes Encontrados")
    @ApiResponse(responseCode = "404", description = "Clientes não Encontrados, Tente Novamente!")
    @GetMapping
    public ResponseEntity<List<Contato>> getAll() {
        List<Contato> contatos = contatoService.getAll();
        return ResponseEntity.ok(contatos);
    }
    @Operation(summary = "Buscar todos os clientes Por id", description = "Retorna uma lista com todos os clientes cadastrados por id ")
    @ApiResponse(responseCode = "200", description = "Cliente Encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não Encontrado, Tente Novamente!")
    @GetMapping("/{id}")
    public ResponseEntity<Contato> getById(@PathVariable Long id) {
        Contato contato = contatoService.getById(id);
        return ResponseEntity.ok(contato);
    }
    
    @Operation(summary = "Cadastrar um novo contato", description = "Cadastrar um novo contato")
    @ApiResponse(responseCode = "200", description = "Cliente cadastrado")
    @ApiResponse(responseCode = "404", description = "Clientes não cadastrado, Tente Novamente!")
    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato) {
        Contato novoContato = contatoService.create(contato);
        return ResponseEntity.ok(novoContato);
    }


    @Operation (summary = "Atualizar Contato por id", description = "Atualiza um Contato por id")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado")
    @ApiResponse(responseCode = "404", description = "Cliente não atualizado, Tente Novamente!")
    @PutMapping("/{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {

        Contato contatoUpdate = contatoService.getById(id);

        if (contatoUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        if (contatoUpdate.getNome() != null) {
            contatoUpdate.setNome(contato.getNome());
        }

        if (contatoUpdate.getEmail() != null) {
            contatoUpdate.setEmail(contato.getEmail());
        }

        contatoService.create(contatoUpdate);
        return ResponseEntity.ok(contatoService.getById(id));
    }

    @Operation(summary = "Deletar um contato por id", description = "Deleta um contato por id")
    @ApiResponse(responseCode = "200", description = "Cliente deletado")
    @ApiResponse(responseCode = "404", description = "Clientes não deletado, Tente Novamente!")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Contato contato = contatoService.getById(id);
        if (contato == null) {
            return ResponseEntity.notFound().build();
        }
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
