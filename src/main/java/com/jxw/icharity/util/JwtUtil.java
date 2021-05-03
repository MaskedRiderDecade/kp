package com.jxw.icharity.util;

import com.jxw.icharity.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import static com.jxw.icharity.constants.Constants.AUTH_PREFIX;

@Slf4j
public class JwtUtil {

    //jwt签名算法
    public static final Key key= Keys.hmacShaKeyFor(Decoders.BASE64.decode("eskpjxwsefgesfgvccharitysegfjiQ=sedfSZDFsedfusekjfh"));

    public String createJwtToken(User user){
        val now=System.currentTimeMillis();
        return Jwts.builder()
                .setId("ihouse")
                .claim("userId",user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now+5184000L))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Claims body= (Claims) Jwts.parserBuilder().setSigningKey(key).build().parse(token).getBody();
            return true;
        }catch(ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return false;
        }

    }

    public String getUsername(String token){
        try{
            Jwt jwt=Jwts.parserBuilder().setSigningKey(key).build().parse(token.replace(AUTH_PREFIX, ""));
            Claims body= (Claims) jwt.getBody();
            return body.getSubject();
        }catch(ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return null;
        }
    }

    public Integer getUserId(String token){
        try{
            Jwt jwt=Jwts.parserBuilder().setSigningKey(key).build().parse(token.replace(AUTH_PREFIX, ""));
            Claims body= (Claims) jwt.getBody();
            return (Integer) body.get("userId");
        }catch(ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return null;
        }
    }

}
