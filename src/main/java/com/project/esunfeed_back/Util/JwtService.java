package com.project.esunfeed_back.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.esunfeed_back.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtService {

    private Key secretKey;

    @Value("${conf.token.secret}")
    private String secret;

    @Value("${conf.token.expiration}")
    private long tokenExpiration; // Token 有效期

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 簽發Token
     */
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername()) // 以 Username 做為 Subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) // 使用 tokenExpiration
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(
            Map<String, Object> extractClaims,
            User user) {

        extractClaims.put("userid", user.getUserId());

        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUserEmail()) // 以 Username 做為 Subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) // 使用 tokenExpiration
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername()) // 以 Username 做為 Subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) // 使用 tokenExpiration
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String email, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return generateToken(claims, email, tokenExpiration); // 使用類中的 tokenExpiration
    }

    private String generateToken(Map<String, Object> claims, String subject, long expirationMillis) {
        return Jwts.builder()
                .setClaims(claims) // 設置自訂的 Claims
                .setSubject(subject) // 設置 Subject 為用戶名
                .setIssuedAt(new Date(System.currentTimeMillis())) // 設置簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis)) // 設置到期時間
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // 使用固定的密鑰和 HMAC SHA-256 簽名演算法對 JWT 進行簽名
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey) // Ensure this is your correct secret key
            .build()
            .parseClaimsJws(token)
            .getBody();
    } catch (MalformedJwtException e) {
        System.err.println("Malformed JWT token: " + token);
        // Optionally, log or throw a custom exception
        throw new IllegalArgumentException("Invalid JWT token", e);
    }
    }

    private Key getSignInKey() {
        return secretKey; // 返回密鑰
    }
}