package com.bank.approve.domain.entitys;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.components.CustomAuthorityDeserializer;
import com.bank.approve.domain.components.NumberAgency;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrator implements UserDetails{

    private String cpf;

    private String rg;

    private String password;

    private NumberAgency numberAgency;

    private String nameComplete;

    private String role;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
       return this.cpf;
    }
    @Override
    public boolean isAccountNonExpired() {
      return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }
    @Override
    public boolean isEnabled() {
       return true;
    }
}
