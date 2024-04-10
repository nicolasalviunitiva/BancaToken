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
import jakarta.ws.rs.core.Response;

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
    public Response accredito(@QueryParam("id_utente") Long id, @QueryParam("importo") BigDecimal importo) {        
        Utente utente = utenteService.findById(id);
        try { 
            BigDecimal somma = utente.getConto().getSaldo().add(importo);
            utente.getConto().setSaldo(somma);
            return Response.ok("Versamento effettuato").build();
        } catch (NullPointerException e ) {
            return Response.status(400).entity("ERRORE! verificare il conto corrente").build();
        }
    }

    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/prelievo")
    public Response prelievo(@QueryParam("id_utente") Long id, @QueryParam("importo") BigDecimal importo) {
        try {
            BigDecimal saldo = utenteService.findById(id).getConto().getSaldo();
            if (saldo.compareTo(importo) > 0) {
                Utente utente = utenteService.findById(id);
                utente.getConto().setSaldo(saldo.subtract(importo));
                return Response.ok("Prelievo effettuato").build();
            } else {
                return Response.status(500).entity("ERRORE! Credito insufficiente").build();
            }
        }  catch (NullPointerException e ) {
            return Response.status(400).entity("ERRORE! verificare il conto corrente").build();
        }
    }

    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/accredito")
    public Response accredito(@QueryParam("id_utente") Long idUtente, @QueryParam("id_conto") Long idConto, @QueryParam("importo") BigDecimal importo) {
        Conto conto = contoService.findById(idConto);
        try {
            if (conto != null) {
                Utente utente = utenteService.findById(idUtente);
                if (utente.getConto().getSaldo().compareTo(importo) > 0) {
                    utente.getConto().setSaldo(utente.getConto().getSaldo().subtract(importo));
                    conto.setSaldo(conto.getSaldo().add(importo));
                    return Response.ok("Accredito effettuato").build();
                } else {
                    return Response.status(500).entity("ATTENZIONE! Transazione negata, credito insufficiente").build();
                }
            } else {
                return Response.status(400).entity("ATTENZIONE! Transazione negata, conto Corrente errato").build();
            }
        } catch (NullPointerException e ) {
            return Response.status(400).entity("ERRORE! verificare il conto corrente").build();
        }
    }

    

}
