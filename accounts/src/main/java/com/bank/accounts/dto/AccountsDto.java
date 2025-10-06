package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts",description = "Info regarding Accounts")
public class AccountsDto {

    @Schema(description = "Account number")
    @NotEmpty(message = "Account number cannot be null and empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
    private long accountNumber;

    @Schema(description = "Type of account", example = "Savings")
    @NotEmpty(message = "Account type cannot be null and empty")
    private String accountType;

    @Schema(description = "Address of branch where account exists", example = "Saharanpur Chowk Branch")
    @NotEmpty(message = "Branch address cannot be null and empty")
    private String branchAddress;
}
