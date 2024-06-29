package br.com.tce.desafiopitangbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtil  {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Método para extrair o nome de usuário do token JWT
    public String extractUsername(String token) {
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    // Método para extrair a data de expiração do token JWT
    public Date extractExpiration(String token) {
        try {
            return JWT.decode(token).getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    // Método para verificar se o token JWT expirou
    private Boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration != null && expiration.before(new Date());
    }

    // Método para gerar um token JWT para o usuário
    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    // Método para criar o token JWT
    private String createToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC256(secret));
    }

    // Método para validar o token JWT
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
