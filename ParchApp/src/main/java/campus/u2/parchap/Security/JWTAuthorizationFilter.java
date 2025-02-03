/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.parchap.Security;



import static campus.u2.parchap.Security.Constants.HEADER_AUTHORIZACION_KEY;
import static campus.u2.parchap.Security.Constants.TOKEN_BEARER_PREFIX;
import static campus.u2.parchap.Security.Constants.TOKEN_EXPIRATION_TIME;
import static campus.u2.parchap.Security.Constants.getSigningKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private boolean isJWTValid(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
            return false;
        }
        try {
            String token = authenticationHeader.replace(TOKEN_BEARER_PREFIX, "");
            // Verifica la firma del token
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;  // Token inválido o expirado
        }
    }

    private Claims setSigningKey(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Obtienes la clave para validar el JWT
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();  // Extraes las claims del JWT
    }

    private void setAuthentication(Claims claims) {
        String username = claims.getSubject();  // El "subject" será el correo electrónico

        // No es necesario obtener roles si no tienes ese campo en el token
        UsernamePasswordAuthenticationToken auth
                = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()); // No asignamos roles

        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authenticationHeader = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY);

        if (authenticationHeader == null || !authenticationHeader.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
            System.out.println("Token no encontrado o formato incorrecto.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authenticationHeader.replace(Constants.TOKEN_BEARER_PREFIX, "");
        System.out.println("Token recibido: " + token);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Constants.getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("Token validado y autenticación configurada.");
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Token ha expirado. Inicie sesión nuevamente.\"}");
            return;
        } catch (UnsupportedJwtException e) {
            System.out.println("Token no soportado: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Token no soportado.\"}");
            return;
        } catch (MalformedJwtException e) {
            System.out.println("Token mal formado: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Token mal formado.\"}");
            return;
        } catch (JwtException e) {
            System.out.println("Error en el token: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Error en la autenticación del token.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

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

