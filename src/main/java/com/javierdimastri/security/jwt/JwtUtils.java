package com.javierdimastri.security.jwt;

import com.javierdimastri.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.app.jwtSecret}")
    private String jwtSecret;

    @Value("${security.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException error) {
            logger.error("Invalid JWT signature: {}", error.getMessage());
        } catch (MalformedJwtException error) {
            logger.error("Invalid JWT token: {}", error.getMessage());
        } catch (ExpiredJwtException error) {
            logger.error("JWT token is expired: {}", error.getMessage());
        } catch (UnsupportedJwtException error) {
            logger.error("JWT token is unsupported: {}", error.getMessage());
        } catch (IllegalArgumentException error) {
            logger.error("JWT claims string is empty: {}", error.getMessage());
        }

        return false;
    }
}
