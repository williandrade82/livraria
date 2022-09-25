package br.com.fiap.livraria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    public String generateToken(String username){

        Date dataCriacao = getDateFromLocalDatetime(LocalDateTime.now());
        Date dataExpiracao = getDateFromLocalDatetime(LocalDateTime.now().plusMinutes(expire));
        return Jwts.builder()
                .addClaims(new HashMap<>()) //Roles
                .setSubject(username)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer",""))
                .getBody();
        return claims.getSubject();
    }
    private Date getDateFromLocalDatetime(LocalDateTime localDateTime){
        return Date.from(localDateTime.toInstant(OffsetDateTime.now().getOffset()));
    }
}
