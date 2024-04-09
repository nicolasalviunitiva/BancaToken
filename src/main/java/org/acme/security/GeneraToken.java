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

     String tokenUser =
           Jwt.issuer("UNITIVA") 
             .upn("NICOLA") //IMPOSTA IL NOME UTENTE DEL TOKEN
             .groups("User")
             .expiresIn(600) //impostazione durata 10 min
           .sign();

    String tokenAdmin =
           Jwt.issuer("UNITIVA") 
             .upn("NICOLA") //IMPOSTA IL NOME UTENTE DEL TOKEN
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