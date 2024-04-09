package org.acme.service;

import org.acme.model.Utente;
import org.acme.repository.UtenteRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtenteService {

    private UtenteRepository utenteRepository;

    public UtenteService (UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    public void newUtente (Utente utente){      
        utenteRepository.persist(utente);  
    }

    public Utente findById (Long id){
        return utenteRepository.findById(id);
    }

    public void deleteById (Long id){
         utenteRepository.deleteById(id);

    }

}
