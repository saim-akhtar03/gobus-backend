package com.tickets.GoBus.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {
    SecretKey key= Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> autherities=auth.getAuthorities();
        String roles =populateAuthorities(autherities);
        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+84600000))
                .claim("email",auth.getName())
                .claim("autheroties",roles)
                .signWith(key)
                .compact();

    }
    public String getEmailFromJwtToken(String jwt ){
        jwt=jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> autherities) {
        Set<String> auths=new HashSet<>();
        for(GrantedAuthority authority:autherities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }
}
