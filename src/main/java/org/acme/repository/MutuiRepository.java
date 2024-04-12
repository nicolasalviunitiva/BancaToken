package org.acme.repository;

import java.util.List;

import org.acme.model.Mutui;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MutuiRepository implements PanacheRepositoryBase <Mutui,Long>{

    public List<Mutui> getAll (){
        return listAll();
    }

}
