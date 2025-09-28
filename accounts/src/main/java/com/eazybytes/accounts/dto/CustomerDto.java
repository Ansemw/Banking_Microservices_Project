package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer details",
description = "Holds info regarding customers and their respective accounts")
public class CustomerDto {

    @Size(min = 5,max = 30,message = "Name length should be between 5 and 30")
    @NotEmpty(message = "Customer name can't be null or empty")
    @Schema(description = "Name of customer", example = "Anurag Semwal")
    private String name;

    @Email(message = "Please enter valid email")
    @NotEmpty(message = "Email can't be null or empty")
    @Schema(description = "Email of customer", example = "anurag.semwal@gmail.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
    @Schema(description = "Mobile number of customer", example = "9412032423")
    private String mobileNumber;

    @Schema(description = "Account Information")
    private AccountsDto accountsDto;
}
