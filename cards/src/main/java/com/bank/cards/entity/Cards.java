package com.bank.cards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "cards")
public class Cards extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;
    private String mobileNumber;
    private long cardNumber;
    private String cardType;
    private long totalLimit;
    private long amountUsed;
    private long availableAmount;
}
