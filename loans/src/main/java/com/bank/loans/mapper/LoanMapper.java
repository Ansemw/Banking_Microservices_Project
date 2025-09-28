package com.bank.loans.mapper;

import com.bank.loans.dto.LoanDto;
import com.bank.loans.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto){

        loanDto.setLoanType(loan.getLoanType());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {

        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());

        return loan;
    }
}
