package com.examen.examenJava.security.jwt;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.examen.examenJava.security.services.UserPrinciple;

import io.jsonwebtoken.*;

@Component
public class JwtProvider {
	
	private static final Log log = LogFactory.getLog(JwtProvider.class);
	
    @Value("123456")
    private String jwtSecret;
    
    @Value("20")
    private int jwtExpiration;
    
    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
        	log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
        	log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
        	log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
        	log.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey(jwtSecret)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
}
