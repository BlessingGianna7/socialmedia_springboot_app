package com.example.socialmedia.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer("Gianna")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt; // Remove 'Bearer ' prefix if present
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt) // Changed parseClaimsJwt to parseClaimsJws
                .getBody();

        return claims.get("email", String.class); // Changed to directly get the email claim as String
    }
}
