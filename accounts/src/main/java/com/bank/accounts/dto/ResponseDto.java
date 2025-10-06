package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "API Response", description = "Response structure for all api")
public class ResponseDto {

    @Schema(description = "API Response status code", example = "200")
    private String statusCode;
    @Schema(description = "API Response status message", example = "OK")
    private String statusMessage;
}
