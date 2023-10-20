package com.bank.borrowing.domain.entity.official.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.borrowing.domain.borrowing.CustomAuthorityDeserializer;
import com.bank.borrowing.domain.entity.client.model.Address;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Official implements UserDetails {
    @Id
    private String cpf;

    private String rg;

    private String nameComplete;

    private String email;

    private String password;

    private Address address;

    private String role;

    private Boolean isAuthorized;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Official(String cpf, String rg, String nameComplete, String email, String password,
            Address address) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("Formato do cpf inválido");
        }
        if (rg.length() != 11) {
            throw new IllegalArgumentException("RG com formato inválido");

        }
        this.cpf = cpf;
        this.rg = rg;
        this.nameComplete = nameComplete;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = "ROLE_OFFICIAL";
        this.isAuthorized = false;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
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
        return this.isAuthorized;
    }
}
