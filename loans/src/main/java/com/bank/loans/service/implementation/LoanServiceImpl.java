package com.bank.loans.service.implementation;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.entity.Loan;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.mapper.LoanMapper;
import com.bank.loans.repository.LoanRepository;
import com.bank.loans.service.IloanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements IloanService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public void createNewLoan(String mobileNumber) {
        Loan loan=createLoan(mobileNumber);
        loanRepository.save(loan);
    }

    private Loan createLoan(String mobileNumber){
        Loan loan= new Loan();
        long loanNumber=1000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(loanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoanConstants.HOME_LOAN);
        loan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        return loan;
    }

    @Override
    public LoanDto readLoan(String mobileNumber) {
        Optional<Loan> loanOptional=loanRepository.findByMobileNumber(mobileNumber);

        if (loanOptional.isPresent()){

            LoanDto loanDto=new LoanDto();
            Loan loan=loanOptional.get();
            return LoanMapper.mapToLoanDto(loan, loanDto);
        }


         else throw new ResourceNotFoundException("loan","mobileNumber",mobileNumber);
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        boolean isUpdated=false;
        Optional<Loan> loanOptional=loanRepository.findByMobileNumber(loanDto.getMobileNumber());
        if(loanOptional.isEmpty()){
            throw new ResourceNotFoundException("loan","mobileNumber",loanDto.getMobileNumber());
        }
        Loan loan=loanOptional.get();
        loanRepository.save(LoanMapper.mapToLoan(loanDto,loan));
        isUpdated=true;
        return isUpdated;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted=false;
        Optional<Loan> loanOptional=loanRepository.findByMobileNumber(mobileNumber);
        if(loanOptional.isEmpty()){
            throw new ResourceNotFoundException("loan","mobileNumber",mobileNumber);
        }
        loanRepository.deleteById(loanOptional.get().getLoanId());
        isDeleted=true;
        return isDeleted;
    }
}
