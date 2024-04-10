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

    public boolean newUtente (Utente utente){      
        if(utente.getNome()!=null && utente.getCognome()!=null && utente.getEta()!=null){
            utenteRepository.persist(utente);  
            return true;
        } else {
            return false;
        }
    }

    public Utente findById (Long id){
        return utenteRepository.findById(id);
    }

    public boolean deleteById (Long id){
         return utenteRepository.deleteById(id);

    }

}
