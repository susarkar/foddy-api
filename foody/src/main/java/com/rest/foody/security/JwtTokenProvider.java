package com.rest.foody.security;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;


@Component
@Log4j2
public class JwtTokenProvider {
	private String secret = "JWT-S3CR3T-AIP3!!";

	private Duration expiraton =  Duration.ofMillis(60 * 60 * 24 * 1000 * 7);

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = Date.from(Instant.ofEpochMilli(now.getTime() + expiraton.toMillis()));

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setId(userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
        	log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	log.error("JWT claims string is empty.");
        }
        return false;
    }
}
