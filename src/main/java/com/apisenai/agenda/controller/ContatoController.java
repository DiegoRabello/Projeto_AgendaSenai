package com.apisenai.agenda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.apisenai.agenda.classes.Contato;
import com.apisenai.agenda.service.ContatoService;

@RestController
@RequestMapping("contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<Contato>> getAll() {
        List<Contato> contatos = contatoService.getAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getById(@PathVariable Long id) {
        Contato contato = contatoService.getById(id);
        return ResponseEntity.ok(contato);
    }

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato) {
        Contato novoContato = contatoService.create(contato);
        return ResponseEntity.ok(novoContato);
    }

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
