package com.apisenai.agenda.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apisenai.agenda.repository.ContatoRepository;
import com.apisenai.agenda.dto.ContatoDto;
import com.apisenai.agenda.dto.ContatoUpdateDto;

import com.apisenai.agenda.classes.Contato;

@Service
public class ContatoService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ContatoRepository contatoRepository;
//diego dps dá um push blz :)
    public void sendEmailNotification (Contato contato) {
        
        
    }
    public List<Contato> getAll(){
        return contatoRepository.findAll();
    }

    public Contato save(Contato contato){
        return contatoRepository.save(contato);
    }

    public Contato getById(Long id){
        return contatoRepository.findById(id).orElse(null);
    }

    public Contato create(Contato contato){
        Contato contatoSalvo = contatoRepository.save(contato);
        return contatoSalvo;
    }
    
    public Contato delete(Long id){
        contatoRepository.deleteById(id);
        Contato contato = getById(id);
        return contatoRepository.save(contato);
    }

    public Contato update(Long id, Contato contatoExistente, Contato contatoNovo){

        if(contatoNovo.getNome() != null){
            contatoExistente.setNome(contatoNovo.getNome());
        }
        if(contatoNovo.getEmail() != null){
            contatoExistente.setEmail(contatoNovo.getEmail());
        }

        return contatoRepository.save(contatoExistente);
    }

    public ContatoUpdateDto updateDto(Contato contatoExistente, ContatoUpdateDto contatoNovo){
        
        if(contatoNovo.getNome() != null){
            contatoExistente.setNome(contatoNovo.getNome());
        }
        if(contatoNovo.getEmail() != null){
            contatoExistente.setEmail(contatoNovo.getEmail());
        }
        Contato contatoSalvo = contatoRepository.save(contatoExistente);

        ContatoUpdateDto contatoDto = new ContatoUpdateDto();
        contatoDto.setNome(contatoSalvo.getNome());
        contatoDto.setEmail(contatoSalvo.getEmail());

        return contatoDto; 

    }

    public List<ContatoDto> getContatosDto(){ 
        
        List<Contato> contatos = contatoRepository.findAll();

        List<ContatoDto> contatosDto = new ArrayList<>();

        for(Contato contato : contatos){
            ContatoDto contatoDto = new ContatoDto();
            contatoDto.setNome(contato.getNome());
            contatosDto.add(contatoDto);
        }
        return contatosDto;
    }
}
