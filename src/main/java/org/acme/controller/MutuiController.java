package org.acme.controller;

import java.math.BigDecimal;
import java.util.List;

import org.acme.model.Conto;
import org.acme.model.Mutui;
import org.acme.model.Utente;
import org.acme.service.ContoService;
import org.acme.service.MutuiService;
import org.acme.service.UtenteService;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mutui")
public class MutuiController {

    private MutuiService mutuiService;

    private ContoService contoService;

    private UtenteService utenteService;

    public MutuiController(MutuiService mutuiService, ContoService contoService, UtenteService utenteService){
        this.mutuiService = mutuiService;
        this.contoService = contoService;
        this.utenteService = utenteService;
    }

   // @Scheduled(cron = "0 0 0 * * ?")
   @Scheduled(every = "10s", identity = "task-job")
   @Transactional
    public void addebitoRataMutuo() {
        /*
         * TODO: - accesso alla tabella mutui
         * - controlli scadenza per rata
         * - accesso al conto del utente
         * - verifica solubilita e addebito
         */

        List<Mutui> listaMutui = mutuiService.getAllMutuiScaduti();

        for (Mutui mutuo : listaMutui) {
            if (mutuo.getRatePagate() < mutuo.getnRate()) {

                Long idConto = utenteService.findById(mutuo.getIdUser()).getConto().getId();
                Conto conto = contoService.findById(idConto);
                System.out.println("conto 1° accesso "+conto);
                BigDecimal saldo = conto.getSaldo();
                BigDecimal impRata = mutuo.getImpRata();

                if (saldo.compareTo(impRata) > 0) {
                    contoService.addebito(impRata, idConto);

                    mutuo.setRatePagate(mutuo.getRatePagate() + 1);
                    Log.info("_____ rate pagate: "+mutuo.getRatePagate());
                    mutuiService.upDate(mutuo);
                    Log.info("Rata pagata: "+utenteService.findById(mutuo.getIdUser()));

                } else {
                    Log.error("Rata non pagata per insolubilita utente: "+utenteService.findById(mutuo.getIdUser()));
                }
            }
        }
    }

}
