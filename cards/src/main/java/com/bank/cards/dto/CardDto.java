package com.bank.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CardDto {

    private long cardId;
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
    private String mobileNumber;


    private long cardNumber;

    @NotEmpty
    private String cardType;


    private long totalLimit;
    private long amountUsed;
    private long availableAmount;
}
