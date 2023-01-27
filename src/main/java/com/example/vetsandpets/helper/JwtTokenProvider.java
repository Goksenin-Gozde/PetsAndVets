package com.example.vetsandpets.helper;


import com.example.vetsandpets.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "secret";

    private final long JWT_EXPIRATION = 604800000L;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

//        return JWT.create()
//                .withSubject(Long.toString(user.getId()))
//                .withIssuedAt(new Date())
//                .withExpiresAt(expiryDate)
//                .sign(Algorithm.HMAC512(JWT_SECRET.getBytes()));
        return "";
    }

    public Long getUserIdFromJWT(String token) {
//        DecodedJWT decodedJWT = JWT.decode(token);
//        return Long.parseLong(decodedJWT.getSubject());
        return 1L;
    }

    public boolean validateToken(String authToken) {
        try {
//            JWT.decode(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
