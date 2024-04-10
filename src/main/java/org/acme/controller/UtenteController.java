package org.acme.controller;

import org.acme.model.Utente;
import org.acme.service.ContoService;
import org.acme.service.UtenteService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/utente")
public class UtenteController {

    private UtenteService utenteService;
    private ContoService contosService;

    public UtenteController (UtenteService utenteService, ContoService contosService){
        this.utenteService = utenteService;
        this.contosService = contosService;
    }

    @POST
    @Transactional
    @Path("/newutente")
    public Response newUtente (Utente utente){
        boolean esito = utenteService.newUtente(utente);
        if(esito == true) {
            return Response.ok("Utente creato correttamente").build();
        } else {
            return Response.status(500).entity("ERRORE! tutti i campi sono obbligatori!").build();
        }
    }

    @GET
    @Path("/find{id}")
    public Utente getUtente (@PathParam("id") Long id){
        return utenteService.findById(id);
    }

    

    @GET
    @Transactional
    @RolesAllowed("Admin")
    @Path("/delete{id}")
    @SuppressWarnings("finally")
    public Response deleteUtente (@PathParam("id") Long id){
    try{
        if (utenteService.findById(id).getConto().getId() != null){
            Long idConto = utenteService.findById(id).getConto().getId();
            contosService.deleteById(idConto);
        }
    } finally {
        boolean esito = utenteService.deleteById(id);
        if (esito == true ){
            return Response.ok("Utente eliminato correttamente!").build();
        } else {
            return Response.status(400).entity("ID utente non trovato").build();
        }
    }
    }

}
