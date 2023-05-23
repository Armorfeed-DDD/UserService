package com.amorfeed.api.userservice.config;

import com.amorfeed.api.userservice.entity.User;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {

    private static final Logger logger= LoggerFactory.getLogger(JwtHandler.class);

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    @Getter
    @Setter
    private String validationMessage;

    public String generateToken(Authentication authentication){
        String subject=((UserDetailsImpl) authentication.getPrincipal()).getEmail();
        Date issuedAt=new Date();
        Date expiration= DateUtils.addDays(issuedAt, expirationDays);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFrom(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token);
            validationMessage = "Sucessfull authentication";
            return true;
        } catch (SignatureException e){
            validationMessage = "Invalid JSON Web Token Signature";
            logger.error("Invalid JSON Web Token Signature: {}", e.getMessage());
        } catch (MalformedJwtException e){
            validationMessage = "Invalid JSON Web Token";
            logger.error("Invalid JSON Web Token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            validationMessage = "JSON Web Token is expired";
            logger.error("JSON Web Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            validationMessage = "JSON Web Token is unsupported";
            logger.error("JSON Web Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            validationMessage = "JSON Web Token claims string is empty";
            logger.error("JSON Web Token claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
