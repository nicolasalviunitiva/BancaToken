package org.acme.controller;


import org.acme.model.Conto;
import org.acme.service.ContoService;
import org.acme.service.UtenteService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/conto")
public class ContoController {

    private ContoService contoService;

    public ContoController (ContoService contoService, UtenteService utenteService){
        this.contoService = contoService;
    }

    @POST
    @Transactional
    @Path("/newconto")
    public Response newConto (Conto conto, @QueryParam("id_utente") Long idUtente ){
        boolean esito = contoService.newConto(conto,idUtente);
        if (esito == true){
            return Response.ok("Il conto è stato creato correttamente!").build();
        } else {
            return Response.status(500).entity("ATTENZIONE! L'utente ha già un conto corrente.").build();
        }
    }    

    @GET
    @Path("/find{id}")
    public Conto findConto (@PathParam("id") Long id){
        return contoService.findById(id);
    }

    @GET
    @RolesAllowed("Admin")
    @Transactional
    @Path("/delete{id}")
    public Response deleteConto (@PathParam("id") Long id){
        boolean esito = contoService.deleteById(id);
        if (esito == true){
            return Response.ok("Il conto è stato eliminato correttamente!").build();
        } else {
            return Response.status(400).entity("ATTENZIONE! Il conto corrente non è stato trovato.").build();
        }
    }

}
