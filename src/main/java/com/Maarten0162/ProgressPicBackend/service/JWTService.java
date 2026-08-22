package com.Maarten0162.ProgressPicBackend.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.Maarten0162.ProgressPicBackend.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private final SecretKey key;

    public JWTService(@Value("${jwt.secret}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(
            jwtSecret.getBytes(StandardCharsets.UTF_8)
        );
    }


    public String generateToken(User user) {
        return Jwts.builder() //creates jwt token
                .subject(user.getUuid().toString()) // zet de sub claim naar de uuid om aan te geven bij wij de token hoord en kan worden gebruikt om te validaten of een gebruiker bij een request mag
                .claim("email", user.getEmail()) //extra claim voor het email 
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1 uur
                .signWith(key)
                .compact(); // maakt er 1 string van
    }
}
