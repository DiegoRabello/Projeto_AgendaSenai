package com.apisenai.agenda.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisenai.agenda.classes.Evento;
import com.apisenai.agenda.repository.EventoRepository;
@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;
    
    public List<Evento> getAll() {
       return eventoRepository.findAll();
    }

    public Evento getById (Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento getByDataHora(LocalDateTime dataHora) {
        return eventoRepository.findByDataHora(dataHora).orElse(null);
    }
    
    public List<Evento> getEventosPassados() {
        return eventoRepository.findByDataHoraFimLessThanEqual(LocalDateTime.now()).orElse(null);
    }

    public List<Evento> getEventosFuturos() {
        return eventoRepository.findByDataHoraInicioGreaterThanEqual(LocalDateTime.now()).orElse(null);
    }

    public Evento create (Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento update(Long id, Evento evento) {
        Evento eventoUpdate = getById(id);
        if (eventoUpdate == null){
            return null;
        }
        eventoUpdate.setNome(evento.getNome());
        eventoUpdate.setDescricao(evento.getDescricao());
        eventoUpdate.setDataHoraInicio(evento.getDataHoraInicio());
        eventoUpdate.setDataHoraFim(evento.getDataHoraFim());

        return eventoRepository.save(eventoUpdate);
    }
      
    public Evento delete (Long id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
       if (evento == null) {
        return null;
   
        }
        eventoRepository.delete(evento);
        return evento;
    }
}
