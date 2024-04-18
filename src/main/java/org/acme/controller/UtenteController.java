package org.acme.controller;

import org.acme.model.Utente;
import org.acme.service.UtenteService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/utente")
public class UtenteController {

    private UtenteService utenteService;

    public UtenteController (UtenteService utenteService){
        this.utenteService = utenteService;
    }

    @Inject
    @Channel("new-utente")
    Emitter<String> userEmitter;

    @POST
    @Transactional
    @Path("/newutente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUtente (Utente utente){
        boolean esito = utenteService.newUtente(utente);
        userEmitter.send(utente.toString());
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
    public Response deleteUtente (@PathParam("id") Long id){
        boolean esito = utenteService.deleteById(id);
        if (esito == true ){
            return Response.ok("Utente eliminato correttamente!").build();
        } else {
            return Response.status(400).entity("ID utente non trovato").build();
        }
    }
    

}
