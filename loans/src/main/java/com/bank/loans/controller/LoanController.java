package com.bank.loans.controller;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.dto.LoansContactInfoDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.service.IloanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
@Validated
public class LoanController {

    @Autowired
    IloanService service;

    @Autowired
    LoansContactInfoDto loansContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                  String mobileNumber){
        service.createNewLoan(mobileNumber);
        ResponseDto responseDto=new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200);
        return ResponseEntity.status(Integer.parseInt(LoanConstants.STATUS_200)).body(responseDto);
    }

    @GetMapping("/read")
    public ResponseEntity<LoanDto> readLoan(@RequestParam
                                                @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                String mobileNumber){
        LoanDto loanDto=service.readLoan(mobileNumber);
        return ResponseEntity.status(200)
                .body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto){
        service.updateLoan(loanDto);
        return ResponseEntity.status(Integer.parseInt(LoanConstants.STATUS_200))
                .body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")                                                      String mobileNumber)
    {
        service.deleteLoan(mobileNumber);
         return ResponseEntity.status(Integer.parseInt(LoanConstants.STATUS_200))
            .body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto);
    }
 }
