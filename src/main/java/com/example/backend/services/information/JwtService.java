package com.example.backend.services.information;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.backend.exception.CustomMessageException;
import com.example.backend.models.entity.Customer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@Slf4j
public class JwtService {

    @Value("${token.secret.key}")
    String jwtSecretKey;

    @Value("${token.expirationms}")
    Long jwtExpirationMs;
    //add push alskdjflk;alksdfjklajsd fkla;dkjf
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractId(String token) {return extractClaim(token, (Claims::getId));}

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(UserDetails userDetails, Customer customer) {
        return generateToken(new HashMap<>(), userDetails, customer);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Customer customer) {
//        extraClaims.put("id", customer.getId());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setId(customer.getId().toString())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException ex) {
            throw new CustomMessageException("Token expiration", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }catch (UnsupportedJwtException ex){
            throw new CustomMessageException("Token is not support.", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }catch (MalformedJwtException | SignatureException ex) {
            throw new CustomMessageException("Token is invalid format.", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        } catch (Exception ex) {
            throw new CustomMessageException(ex.getLocalizedMessage(), String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}