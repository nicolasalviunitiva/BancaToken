package org.acme.controller;

import org.acme.model.RichiestaMutuo;
import org.acme.service.RichiestaService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/richiestamutuo")
public class RichiestaController {

    private RichiestaService richiestaService;
    
    public RichiestaController (RichiestaService richiestaService){
        this.richiestaService = richiestaService;
    }

    @POST
    @Transactional
    @Path("/newrichiesta")
    public String newRichiesta (RichiestaMutuo richiestaMutuo){
        richiestaService.newRichiesta(richiestaMutuo);
        return "ok";
    }    

}

