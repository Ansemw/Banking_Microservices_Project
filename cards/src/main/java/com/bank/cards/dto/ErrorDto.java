package com.bank.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
/**
 * A Data Transfer Object (DTO) for representing error details in the application.
 * This class encapsulates information about an error including the API path,
 * the error message, the HTTP status code, and the timestamp when the error occurred.
 */
public class ErrorDto {

    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;
}
