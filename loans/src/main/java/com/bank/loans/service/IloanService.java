package com.bank.loans.service;

import com.bank.loans.dto.LoanDto;

public interface IloanService {

    public void createNewLoan(String mobileNumber);

    public LoanDto readLoan(String mobileNumber);

    public boolean updateLoan(LoanDto loanDto);

    public boolean deleteLoan(String mobileNumber);
}
