package com.apisenai.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apisenai.agenda.repository.ConviteRepository;

import com.apisenai.agenda.classes.Convite;
import com.apisenai.agenda.classes.Evento;

@Service
public class ConviteService {
    @Autowired
    private ConviteRepository conviteRepository;

    public List<Convite> getAll() {
       return conviteRepository.findAll();
    }

    public Convite getById(Long id) {
        return conviteRepository.findById(id).orElse(null);
    }

    public Convite create(Convite convite) {
        return conviteRepository.save(convite);
    }

    public List<Convite> getByEvento(Evento evento) {
        return conviteRepository.findByEvento(evento).orElse(null);
    }
    
    public Convite update(Long id, Convite conviteExistente, Convite conviteNovo){

        if(conviteNovo.getEvento() != null){
            conviteExistente.setEvento(conviteNovo.getEvento());
        }
        if(conviteNovo.getContato() != null){
            conviteExistente.setContato(conviteNovo.getContato());
        }

        return conviteRepository.save(conviteExistente);
    }
    
    public Convite delete(Long id) {
        conviteRepository.deleteById(id);
        Convite convite = getById(id);
        return conviteRepository.save(convite);
    }
}
