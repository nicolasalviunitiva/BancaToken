package org.acme.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;


@Path("/secured")
@RequestScoped
public class GeneraToken {

  /**
 * Genera un token JWT con le seguenti proprietà:
 *
 * @param issuer L'emittente del token (in questo caso, "Utente").
 * @param upn Il nome utente da impostare nel token.
 * @param groups I gruppi associati all'utente (in questo caso, "User").
 * @param expiresIn La durata del token in secondi (600 secondi = 10 minuti).
 * @return Il token JWT firmato.
 */
     String tokenUser =
           Jwt.issuer("UNITIVA") 
             .upn("Utente") //IMPOSTA IL NOME UTENTE DEL TOKEN
             .groups("User")
             .expiresIn(600) //impostazione durata 10 min
           .sign();

  /**
 * Genera un token JWT con le seguenti proprietà:
 *
 * @param issuer L'emittente del token (in questo caso, "UNITIVA").
 * @param upn Il nome utente da impostare nel token.
 * @param groups I gruppi associati all'utente (in questo caso, "User").
 * @param expiresIn La durata del token in secondi (600 secondi = 10 minuti).
 * @return Il token JWT firmato.
 */
    String tokenAdmin =
           Jwt.issuer("UNITIVA") 
             .upn("Amministratore") //IMPOSTA IL NOME UTENTE DEL TOKEN
             .groups("Admin")
             .expiresIn(600) //impostazione durata 10 min
           .sign();


    @GET
    @Path("/tokenuser")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public String tokenUser (){
        return tokenUser;  
    }
    

    @GET
    @Path("/tokenadmin")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public String tokenAdmin (){
        return tokenAdmin;  
    }
}