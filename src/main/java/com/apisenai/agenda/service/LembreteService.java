package com.apisenai.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.apisenai.agenda.classes.Convite;
import com.apisenai.agenda.classes.Evento;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConviteService conviteService;

    // Verifica os eventos a cada hora
    @Scheduled(fixedRate = 60000) // 3600000 milissegundos = 1 hora
    public void enviarLembretes() {

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umDiaAntes = agora.plusDays(1);
        LocalDateTime umaHoraAntes = agora.plusHours(1);

        // Buscar eventos que ocorrerão em 1 dia e em 1 hora
        List<Evento> eventosUmDiaAntes = eventoService.getEventsByData(umDiaAntes);
        List<Evento> eventosUmaHoraAntes = eventoService.getEventsByData(umaHoraAntes);

        //Enviar lembrete para eventos que ocorrerão em 1 dia
        for (Evento evento : eventosUmDiaAntes) {
            for (Convite convite : conviteService.getByEvento(evento)) { // Assumindo que evento tem uma lista de convites
                emailService.sendEmail(convite.getContato().getEmail(),
                        "Lembrete de Evento",
                        "Lembrete: Seu evento ocorrerá amanhã.");
            }
        }

        // Enviar lembrete para eventos que ocorrerão em 1 hora
        for (Evento evento : eventosUmaHoraAntes) {
            for (Convite convite : conviteService.getByEvento(evento)) { // Acessando todos os convites do evento
                emailService.sendEmail(convite.getContato().getEmail(),
                        "Lembrete de Evento",
                        "Lembrete: Seu evento ocorrerá em 1 hora.");
            }
        }
    }
}