package org.acme.repository;

import org.acme.model.Conto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContoRepository implements PanacheRepositoryBase <Conto,Long>{



}
