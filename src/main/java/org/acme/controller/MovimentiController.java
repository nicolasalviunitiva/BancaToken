package org.acme.controller;

import java.math.BigDecimal;

import org.acme.model.Conto;
import org.acme.model.Utente;
import org.acme.service.ContoService;
import org.acme.service.UtenteService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/movimenti")
public class MovimentiController {

    @Inject
    JsonWebToken jwt;

    private UtenteService utenteService;
    private ContoService contoService;

    public MovimentiController(UtenteService utenteService, ContoService contoService) {
        this.utenteService = utenteService;
        this.contoService = contoService;
    }

    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/versamento")
    public String accredito(@QueryParam("id_utente") Long idUtente, @QueryParam("importo") BigDecimal importo) {
        Utente utente = utenteService.findById(idUtente);
        BigDecimal somma = utente.getConto().getSaldo().add(importo);
        utente.getConto().setSaldo(somma);
        
        return "versamento";
    }

    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/prelievo")
    public String prelievo(@QueryParam("id_utente") Long idUtente, @QueryParam("importo") BigDecimal importo) {
     
        BigDecimal saldo = utenteService.findById(idUtente).getConto().getSaldo();

        if (saldo.compareTo(importo) > 0) {
            Utente utente = utenteService.findById(idUtente);
            utente.getConto().setSaldo(saldo.subtract(importo));
        } else {
            return "Credito insufficiente";
        }

        return "prelievo";
    }

    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/accredito")
    public String accredito(@QueryParam("id_utente") Long idUtente, @QueryParam("id_conto") Long idConto, @QueryParam("importo") BigDecimal importo) {
        Conto conto = contoService.findById(idConto);
        if (conto != null) {
            Utente utente = utenteService.findById(idUtente);
            
            if (utente.getConto().getSaldo().compareTo(importo) > 0) {
                utente.getConto().setSaldo(utente.getConto().getSaldo().subtract(importo));
                conto.setSaldo(conto.getSaldo().add(importo));
            } else {
                return "Credito insufficiente";
            }
        } else {
            return "ATTENZIONE! numeroconto errato!";
        }

        return "acredito";
    }

    

}
