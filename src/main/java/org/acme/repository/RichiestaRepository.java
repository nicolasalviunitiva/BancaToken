package org.acme.repository;

import java.util.List;

import org.acme.model.RichiestaMutuo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RichiestaRepository implements PanacheRepositoryBase <RichiestaMutuo,Long>{

    public List<RichiestaMutuo> findByLavorata(boolean lavorata){
        return list("lavorata", lavorata);
    }

    public List<RichiestaMutuo> findByAccettata(boolean accettata){
        return list("accettata", accettata);
    }
}
