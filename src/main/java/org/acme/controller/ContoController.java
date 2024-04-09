package org.acme.controller;


import org.acme.model.Conto;
import org.acme.service.ContoService;
import org.acme.service.UtenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/conto")
public class ContoController {


    private Logger LOG = LoggerFactory.getLogger(getClass());

    private ContoService contoService;

    public ContoController (ContoService contoService, UtenteService utenteService){
        this.contoService = contoService;
    }

    @POST
    @Transactional
    @Path("/newconto")
    public String newConto (Conto conto, @QueryParam("id_utente") Long idUtente ){
        LOG.info(conto.toString());
        contoService.newConto(conto,idUtente);
    return "conto";
    }    

    @GET
    @Path("/find{id}")
    public Conto findConto (@PathParam("id") Long id){
        return contoService.findById(id);
    }

    @GET
    @Path("/delete{id}")
    public String deleteConto (@PathParam("id") Long id){
        contoService.deleteById(id);
        return "conto";
    }

}
