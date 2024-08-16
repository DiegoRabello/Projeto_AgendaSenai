package com.apisenai.agenda.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apisenai.agenda.repository.ConviteRepository;

import com.apisenai.agenda.classes.Convite;
import com.apisenai.agenda.classes.Evento;

@Service
public class ConviteService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ConviteRepository conviteRepository;

    public List<Convite> getAll() {
       return conviteRepository.findAll();
    }

    public Convite getById(Long id) {
        return conviteRepository.findById(id).orElse(null);
    }

    public Convite create(Convite convite) {
        // return conviteRepository.save(convite);
         // Salvar o convite no banco de dados
         Convite novoConvite = conviteRepository.save(convite);

         // Enviar lembrete imediatamente
         enviarLembrete(novoConvite);
 
         return novoConvite;
    }
    private void enviarLembrete(Convite convite) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataEvento = convite.getEvento().getDataHoraInicio(); // Supondo que o evento tem uma data associada

        if (dataEvento.isBefore(agora.plusDays(1)) && dataEvento.isAfter(agora.plusHours(1))) {
            // Enviar lembrete de que o evento ocorrerá em 1 dia
            emailService.sendEmail(convite.getContato().getEmail(),
                    "Lembrete de Evento",
                    "Lembrete: Seu evento ocorrerá amanhã.");
        } else if (dataEvento.isBefore(agora.plusHours(1))) {
            // Enviar lembrete de que o evento ocorrerá em 1 hora
            emailService.sendEmail(convite.getContato().getEmail(),
                    "Lembrete de Evento",
                    "Lembrete: Seu evento ocorrerá em 1 hora.");
        }
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
