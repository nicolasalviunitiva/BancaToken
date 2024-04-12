package org.acme.controller;

import java.time.LocalDate;
import java.util.List;

import org.acme.enumer.Stati;
import org.acme.model.Mutui;
import org.acme.model.RichiestaMutuo;
import org.acme.service.MutuiService;
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
    private MutuiService mutuiService;


    
    public RichiestaController (RichiestaService richiestaService, MutuiService mutuiService){
        this.richiestaService = richiestaService;
        this.mutuiService = mutuiService;
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
                Mutui mutuo = new Mutui();
                int giorno = richiesta.getDataInizio().getDayOfMonth();
                int mese = richiesta.getDataInizio().getMonthValue()+1;
                int anno = richiesta.getDataInizio().getYear();
                LocalDate data = LocalDate.of(anno,mese,giorno);
                mutuo.setDataInizio(data);
                mutuo.setIdUser(richiesta.getIdUser());
                mutuo.setImpRata(richiesta.getImpRata());
                mutuo.setImporto(richiesta.getImporto());
                mutuo.setnRate(richiesta.getnRate());
                mutuo.setStato("APPROVATO");
                mutuiService.save(mutuo);
                break;
        }
        
        return"ookkk";
        }
}


