package com.bank.approve.domain.components.cardmodel;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
public class Card {

    @Id
    private String numberCard;
    
    @Indexed(unique = true)
    private int cvc;
 
    private String typeCard;

    private String nameComplete;

    private String typeIssuer; // Visa, MasterCard, Elo, Hibercard ou American Express

    private String validityDate;

    private boolean isAuthorized = false;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Card(String numberCard, int cvc, String typeCard, String nameComplete, String typeIssuer,
            String validityDate, Boolean isAuthorized){
        if (cvc <= 100) {
            throw new IllegalArgumentException("CVC com formato errado");
        }
        if (!numberCard.matches("\\d{4}\\ \\d{4}\\ \\d{4}\\ \\d{4}")) {
            throw new IllegalArgumentException("Número de cartão inválido");
        }
        if(typeCard == null){
            throw new IllegalArgumentException("Tipo de cartão vazio");
        }
        if(!typeCard.equals("credit") && !typeCard.equals("debit") && !typeCard.equals("savings") && !typeCard.equals("credit_debit")&& !typeCard.equals("savings_debit")){
            throw new IllegalArgumentException("Tipo de cartão inválido");
        }
        this.typeCard = typeCard;
        this.nameComplete = nameComplete;
        this.typeIssuer = typeIssuer;

        this.numberCard = numberCard;
        this.cvc = cvc;
        this.validityDate = validityDate;
        this.isAuthorized = isAuthorized;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
