package com.bank.approve.infrastructure.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Service
public class TokenService {
    @Value("spring.datasource.secret")
    private String password;
    
    public String getSubject(String token){
        return JWT.require(Algorithm.HMAC256(password))
        .build().verify(token).getSubject();
    }
    public String getIssuer(String token){
        return JWT.require(Algorithm.HMAC256(password))
        .build().verify(token).getIssuer();
    }
}
