package org.acme.repository;

import org.acme.model.Utente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtenteRepository implements PanacheRepositoryBase <Utente,Long> {
    
}
