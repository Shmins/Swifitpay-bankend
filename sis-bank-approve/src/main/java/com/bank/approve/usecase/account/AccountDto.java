package com.bank.approve.usecase.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bank.approve.domain.components.NumberAgency;
import com.bank.approve.domain.components.cardmodel.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private UUID id;

    private List<Card> cards = new ArrayList<>();

    private String typeAccount;

    private NumberAgency numberAgency;

    private String cpf;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
