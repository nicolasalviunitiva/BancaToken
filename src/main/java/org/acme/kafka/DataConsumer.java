package org.acme.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataConsumer {

    @Incoming("utente-incoming")
    public void logUtente (String stringa){
        Log.info(stringa);
    }

}