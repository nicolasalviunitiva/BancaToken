package org.acme.security;

import io.smallrye.jwt.build.Jwt;

public class GeneraToken {

    String token;

    public String getTokenUser (){
        return Jwt.issuer("issuer").upn("UNITIVA").groups("User").sign()  ;
    }
}
