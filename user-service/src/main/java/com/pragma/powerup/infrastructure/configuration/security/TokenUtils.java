package com.pragma.powerup.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;


public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET="Zv9P7PNIcgHfxZaMwQtMpty3TZnmVHRWcsmAMM-mNHg";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS= 2_592_000L;

    public static String createToken(String nombre, String correo){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(correo)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET.getBytes())
                .compact();
    }

   public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return  new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());

        }catch (JwtException e){
            return null;
        }
    }
}
