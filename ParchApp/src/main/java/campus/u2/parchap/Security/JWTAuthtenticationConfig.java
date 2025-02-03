/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.Security;

import static campus.u2.parchap.Security.Constants.TOKEN_EXPIRATION_TIME;
import static campus.u2.parchap.Security.Constants.getSigningKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JWTAuthtenticationConfig {

    public String getJWTToken(String email) {
        String token = Jwts
                .builder()
                .setId("campuscl")
                .setSubject(email)  // Usamos el correo como "subject"
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;  // Devuelve el token con el prefijo "Bearer"
    }
}

