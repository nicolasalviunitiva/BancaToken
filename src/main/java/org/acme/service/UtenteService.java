package org.acme.service;

import org.acme.model.Utente;
import org.acme.repository.ContoRepository;
import org.acme.repository.UtenteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UtenteService {

    private UtenteRepository utenteRepository;

    public UtenteService (UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    @Inject
    private ContoRepository contoRepository;

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

    @SuppressWarnings("finally")
    public boolean deleteById (Long id){
        try{ //Se presente elimina il conto
            if (utenteRepository.findById(id).getConto().getId() != null){
                Long idConto = utenteRepository.findById(id).getConto().getId();
                contoRepository.deleteById(idConto);
            }
        } finally {
           return utenteRepository.deleteById(id);
        }
    }

}
