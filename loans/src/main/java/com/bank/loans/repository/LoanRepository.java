package com.bank.loans.repository;

import com.bank.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {

    Optional<Loan> findByMobileNumber(String mobileNumber);
    boolean deleteByMobileNumber(String mobileNumber);
}
