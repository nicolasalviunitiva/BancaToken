package org.acme.controller;

import java.util.List;

import org.acme.enumer.Stati;
import org.acme.model.RichiestaMutuo;
import org.acme.service.RichiestaService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;


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

    @GET
    @Transactional
    @Path("{stato}")
    public List<RichiestaMutuo> listaRichieste (@PathParam("stato") String stato){
        return richiestaService.findByStato(stato);
    }

    @GET
    @Path("/find{id}")
    public RichiestaMutuo findById (@PathParam("id") Long id){
        return richiestaService.findById(id);
    }


    @GET
    @Transactional
    @Path("/change")
    public String changeStato (@QueryParam("id_richiesta") Long id, @QueryParam("stato") String stato){
        RichiestaMutuo richiesta = richiestaService.findById(id);
        switch (stato) {
            case "ko":
                richiesta.setStato(Stati.RIFIUTATA.getValue());
                break;
            case "ok":
                richiesta.setStato(Stati.ACCETTATA.getValue());
                System.out.println("aggiunta a mutui attivi");
                break;
        }
        
        return"ookkk";
        }
}


