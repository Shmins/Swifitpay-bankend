package com.bank.approve.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.approve.domain.components.Phone;
import com.bank.approve.domain.components.cardmodel.Card;
import com.bank.approve.usecase.account.AccountDto;
import com.bank.approve.domain.components.Address;
import com.bank.approve.domain.components.CustomAuthorityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "client")
public class Client implements UserDetails {
    @Id
    private String cpf;

    private String nameComplete;

    private String email;

    private String password;

    private Phone phone;

    private Address address;

    private Integer accountLimit;
    
    private List<Card> cards;

    private List<AccountDto> account = new ArrayList<>();

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
        return password;
    }

    @Override
    public String getUsername() {
        return cpf;
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
