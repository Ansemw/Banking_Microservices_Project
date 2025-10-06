package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "Errors", description = "Error Response Structure")
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "Path at which error occured")
    private String apiPath;
    @Schema(description = "Error Message")
    private String errorMessage;
    @Schema(description = "Error Message")
    private HttpStatus errorCode;
    @Schema(description = "Error Time")
    private LocalDateTime errorTime;
}
