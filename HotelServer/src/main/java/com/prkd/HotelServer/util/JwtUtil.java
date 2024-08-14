package com.prkd.HotelServer.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private String generateToken(Map<String, Object> extraClaims, UserDetails details){
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000*60*60*24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();

    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode("U2VjcmV0S2V5MTIz");
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
