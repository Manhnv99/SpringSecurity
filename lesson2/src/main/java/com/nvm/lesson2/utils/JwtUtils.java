package com.nvm.lesson2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    private JwtUtils(){}
    private static final SecretKey secretKey= Jwts.SIG.HS256.key().build();
    private static final String ISSUER = "nvm_spring_security";

    public static boolean validateToken(String jwtToken) {
        return parseToken(jwtToken).isPresent();
    }

    private static Optional<Claims> parseToken(String jwtToken) {
        var jwtParser = Jwts.parser().verifyWith(secretKey).build(); //giải mã token với chuỗi token được đưa vào lấy từ request -> giải mã với secret-key
        try{
            return Optional.of(jwtParser.parseSignedClaims(jwtToken).getPayload());
        }catch (JwtException | IllegalArgumentException e){
            log.error("Jwt Exception occurred");
        }
        return Optional.empty();
    }

    public static Optional<String> getUsernameFromToken(String jwtToken) {
        var claimsOptional = parseToken(jwtToken);
        return claimsOptional.map(Claims::getSubject); // lấy username từ chuỗi token được giải mã bằng phương thức có sẵn getSubject
    }

    public static String generateToken(String username) {
        var currentDate=new Date();
        var expiration= DateUtils.addMinutes(currentDate,10);
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(ISSUER)
                .subject(username)
                .signWith(secretKey) //key đăng ký để decode
                .issuedAt(currentDate) //ngày tạo
                .expiration(expiration) //ngày hết hạn
                .compact(); //kết thúc tạo 1 chuỗi token hoàn chỉnh
    }
}
