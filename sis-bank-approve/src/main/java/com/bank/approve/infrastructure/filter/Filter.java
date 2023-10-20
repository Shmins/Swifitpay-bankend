package com.bank.approve.infrastructure.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bank.approve.domain.entitys.Administrator;
import com.bank.approve.domain.entitys.Boss;
import com.bank.approve.infrastructure.token.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Filter extends OncePerRequestFilter {
  @Autowired
  private TokenService tokenService;
  @Autowired
  @Value("${java.hostusers}")
  private String host;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
      throws ServletException, IOException {
    String token;
    var authorization = req.getHeader("Authorization");
    if (authorization != null) {
      token = authorization.replace("Bearer", "");
      var role = this.tokenService.getIssuer(token);
      RestTemplate restTemplate = new RestTemplate();
      switch (role) {
        case ("ROLE_OFFICIAL"): {
          HttpHeaders headers = new HttpHeaders();
          headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
          headers.set("Authorization", authorization);
          final HttpEntity<String> entity = new HttpEntity<>(headers);
          var adm = restTemplate.exchange(String.format("http:%s/official/v1/", host), HttpMethod.GET, entity, Administrator.class).getBody();
          if (adm != null) {
            var auth = new UsernamePasswordAuthenticationToken(adm, null, adm.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
          break;
        }
        case ("ROLE_ADM"): {
          HttpHeaders headers = new HttpHeaders();
          headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
          headers.set("Authorization", authorization);
          final HttpEntity<String> entity = new HttpEntity<>(headers);
          var adm = restTemplate.exchange(String.format("http:%s/adm/v1/", host), HttpMethod.GET, entity, Administrator.class).getBody();
          if (adm != null) {
            var auth = new UsernamePasswordAuthenticationToken(adm, null, adm.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
          break;
        }
        case ("ROLE_BOSS"): {
          HttpHeaders headers = new HttpHeaders();
          headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
          headers.set("Authorization", authorization);
          final HttpEntity<String> entity = new HttpEntity<>(headers);
          var result = restTemplate.exchange(String.format("http:%s/boss/v1/", host), HttpMethod.GET, entity, Boss.class).getBody();
          if (result != null) {

            var auth = new UsernamePasswordAuthenticationToken(result, null, result.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
          break;
        }
        default: {
          throw new IllegalAccessError("Accesso negado");
        }
      }

      res.addHeader("Authorization", token);
    }
    filterChain.doFilter(req, res);
  }

}