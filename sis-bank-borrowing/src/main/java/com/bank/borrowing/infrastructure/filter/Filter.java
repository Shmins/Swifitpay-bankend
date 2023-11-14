package com.bank.borrowing.infrastructure.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.bank.borrowing.domain.entitys.administrator.model.Administrator;
import com.bank.borrowing.domain.entitys.boss.model.Boss;
import com.bank.borrowing.domain.entitys.client.model.Client;
import com.bank.borrowing.domain.entitys.official.model.Official;
import com.bank.borrowing.infrastructure.token.TokenService;
import com.bank.borrowing.usecase.user.UserServiceClient;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Filter extends OncePerRequestFilter {
  @Autowired
  private TokenService tokenService;
  private final UserServiceClient userServiceClient;

  public Filter(TokenService tokenService, UserServiceClient userServiceClient) {
    this.userServiceClient = userServiceClient;
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
      throws ServletException, IOException {
        
    String  authorization = req.getHeader("Authorization");

    if (authorization != null) {
      String token = authorization.replace("Bearer", "");
      
      String username = this.tokenService.getSubject(token);
      var role = this.tokenService.getIssuer(token);

      switch (role) {
        case ("ROLE_CLIENT"): {
          Client client = this.userServiceClient.getClient(username, token);

          var auth = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);

          break;
        }
        case ("ROLE_OFFICIAL"): {
          Official official = this.userServiceClient.getOfficial(username, token);

          var auth = new UsernamePasswordAuthenticationToken(official, null, official.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);

          break;
        }
        case ("ROLE_ADM"): {
          Administrator adm = this.userServiceClient.getAdmintrator(username, token);

          var auth = new UsernamePasswordAuthenticationToken(adm, null, adm.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);

          break;
        }
        case ("ROLE_BOSS"): {
          Boss boss = this.userServiceClient.getBoss(username, token);
          var auth = new UsernamePasswordAuthenticationToken(boss, null, boss.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);

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