package org.acme.service;

import java.util.List;

import org.acme.model.RichiestaMutuo;
import org.acme.repository.RichiestaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RichiestaService {

    @Inject
    private RichiestaRepository richiestaRepository;

    public boolean newRichiesta (RichiestaMutuo richiestaMutui){
        if (richiestaMutui != null){
            richiestaRepository.persist(richiestaMutui);        
            return true;
        } else {
            return false;
        }
    }

    public RichiestaMutuo findById (Long idUser){
        return richiestaRepository.findById(idUser);
    }

    public List<RichiestaMutuo> findByStato(String stato){
        return richiestaRepository.findByStato(stato);
    }

}
