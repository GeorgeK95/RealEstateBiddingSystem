package org.universe.realestatebiddingsystem.app.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.universe.realestatebiddingsystem.app.security.user.UserPrincipal;

import java.util.Date;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value(APP_JWT_SECRET)
    private String jwtSecret;

    @Value(APP_JWT_EXPIRATION_IN_MS)
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error(INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException ex) {
            logger.error(INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException ex) {
            logger.error(EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException ex) {
            logger.error(UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException ex) {
            logger.error(JWT_CLAIMS_STRING_IS_EMPTY);
        }
        return false;
    }
}
