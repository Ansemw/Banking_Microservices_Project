package com.bank.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private long totalLoan;

    private long amountPaid;

    private long outstandingAmount;
}
