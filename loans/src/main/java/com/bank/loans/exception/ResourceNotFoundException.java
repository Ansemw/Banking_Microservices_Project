package com.bank.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, String fieldName, String fieldValue){
        super(resource+" with "+fieldName+" having value "+fieldValue+" not found");
    }
}
