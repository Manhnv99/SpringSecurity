package com.nvm.lesson2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Optional;

@Slf4j
public class JwtUtils {

    private JwtUtils(){}
    private static final SecretKey secretKey= Jwts.SIG.HS256.key().build();

    public static boolean validateToken(String jwtToken) {
        return parseToken(jwtToken).isPresent();
    }

    private static Optional<Claims> parseToken(String jwtToken) {
        var jwtParser = Jwts.parser().verifyWith(secretKey).build();
        try{
            return Optional.of(jwtParser.parseSignedClaims(jwtToken).getPayload());
        }catch (JwtException | IllegalArgumentException e){
            log.error("Jwt Exception occurred");
        }
        return Optional.empty();
    }

    public static Optional<String> getUsernameFromToken(String jwtToken) {
        var claimsOptional = parseToken(jwtToken);
        return claimsOptional.map(Claims::getSubject);
    }
}
