package com.bank.cards.controller;

import com.bank.cards.constants.CardConstants;
import com.bank.cards.dto.CardDto;
import com.bank.cards.dto.ResponseDto;
import com.bank.cards.service.IcardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
@Validated
public class CardsController {

    @Autowired
    IcardsService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                      String mobileNumber){

        service.createCard(mobileNumber);
        return ResponseEntity.status(Integer.parseInt(CardConstants.STATUS_200)).body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
    }

    @GetMapping("/read")
    public ResponseEntity<CardDto> readCard(@RequestParam
                                                @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                String mobileNumber) {

        CardDto cardDto = service.readCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.FOUND).body(cardDto);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody @Valid CardDto cardDto){
        boolean isUpdated=service.updateCard(cardDto);

        if(isUpdated)
            return ResponseEntity.status(Integer.parseInt(CardConstants.STATUS_200))
                    .body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        else
            return ResponseEntity.status(Integer.parseInt(CardConstants.STATUS_417))
                    .body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_UPDATE));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                      String mobileNumber){
        boolean isDeleted=service.deleteCard(mobileNumber);

        if(isDeleted)
            return ResponseEntity.status(Integer.parseInt(CardConstants.STATUS_200))
                    .body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        else
            return ResponseEntity.status(Integer.parseInt(CardConstants.STATUS_417))
                    .body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_DELETE));
    }
}
