package org.acme.service;

import java.math.BigDecimal;

import org.acme.model.Conto;
import org.acme.model.Utente;
import org.acme.repository.ContoRepository;
import org.acme.repository.UtenteRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContoService {

    private ContoRepository contoRepository;
    private UtenteRepository utenteRepository;

    public ContoService (ContoRepository contoRepository, UtenteRepository utenteRepository){
        this.contoRepository = contoRepository;
        this.utenteRepository = utenteRepository;
    }

    public boolean newConto (Conto conto, Long idUtente){
        Utente utente = utenteRepository.findById(idUtente);
        if (utente.getConto() == null){
            contoRepository.persist(conto);
            utente.setConto(conto);
            return true;
        } else {
            return false;
        }
    }

    public Conto findById (Long id){
        return contoRepository.findById(id);
    }

    public boolean deleteById (Long id){
       return contoRepository.deleteById(id);
    }

    public boolean modAttivo (Long id, boolean stato){
        Conto conto = contoRepository.findById(id);
        if (conto != null){
            conto.setAttivo(stato);
            return true;
        } else {
            return false;
        }
    }

    public void upDate (Conto conto){
        contoRepository.persist(conto);
    }

    public void addebito (BigDecimal importo, Long id){
        Conto conto = contoRepository.findById(id);
        conto.setSaldo(conto.getSaldo().subtract(importo));
        upDate(conto);
    }
}
