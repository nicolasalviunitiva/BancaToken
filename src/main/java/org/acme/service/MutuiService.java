package org.acme.service;

import java.time.LocalDate;
import java.util.List;

import org.acme.model.Mutui;
import org.acme.repository.MutuiRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MutuiService {

    private MutuiRepository mutuiRepository;

    public MutuiService (MutuiRepository mutuiRepository){
        this.mutuiRepository = mutuiRepository;
    }

    public void save (Mutui mutuo){
        mutuiRepository.persist(mutuo);
    }

    public List<Mutui> getAll(){
        return mutuiRepository.getAll();
    }

    public List<Mutui> getAllMutuiScaduti(){
        LocalDate oggi = LocalDate.now();
        return mutuiRepository.find("dataInizio", oggi  ).list();
    }

    public void upDate (Mutui mutuo){
        mutuiRepository.persist(mutuo);
    }

}
