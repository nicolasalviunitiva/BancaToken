package org.acme.controller;

import org.acme.model.Utente;
import org.acme.service.UtenteService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/utente")
public class UtenteController {

    private UtenteService utenteService;

    public UtenteController (UtenteService utenteService){
        this.utenteService = utenteService;
    }

    @POST
    @Transactional
    @Path("/newutente")
    public String newUtente (Utente utente){
        utenteService.newUtente(utente);
        return "utente";
    }

    @GET
    @Path("/find{id}")
    public Utente getUtente (@PathParam("id") Long id){
        return utenteService.findById(id); 
    }

    @GET
    @Path("/delete{id}")
    public String deleteUtente (@PathParam("id") Long id){
        utenteService.deleteById(id);
        return "utente";
    }

}
