package com.auth.user.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private final SecretKey secreteKey = Keys.hmacShaKeyFor(
        "chave-auth-jwt".getBytes()
    );

    // Generated a token JWT with roles permissons
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
            .subject(username)
            .claim("roles", roles)
            .expiration(
                Date.from(Instant.now().plusSeconds(3600))
            )
            .signWith(secreteKey)
            .compact();
    }


    //Validated a token JWT expiration time or invalid signature
    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secreteKey)
                .build()
                .parseSignedClaims(token);

            return true;
        }catch (JwtException jwtException) {
            return false;
        }
    }

    //Get a user authentication by token JWT
    public Authentication getAuthentication(String token) {

          Claims claims = Jwts.parser()
            .verifyWith(secreteKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        String username = claims.getSubject();

        List<SimpleGrantedAuthority> authorities =
            ((List<?>) claims.get("roles"))
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();

        return new UsernamePasswordAuthenticationToken(
            username,
            null,
            authorities
        );
    }
}
