package com.kyki.taskmicroservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtUtils {
    private static final String SECRET_KEY = "secret";

    public static String getUsernameFromToken(String token) {
        String cleanToken = token.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(cleanToken);
        return claims.getBody().getSubject();
    }
}
