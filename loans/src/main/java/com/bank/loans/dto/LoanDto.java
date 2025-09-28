package com.bank.loans.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoanDto {

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be null")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;


    private long totalLoan;


    private long amountPaid;


    private long outstandingAmount;
}
