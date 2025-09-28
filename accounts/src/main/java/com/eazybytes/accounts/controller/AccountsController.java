package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "APIs for performing CRUD operations for accounts and customers",
description = "Using these APIs we can create a customer amd account, update them, delete them and " +
        "find info about existing customers and accounts")
@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor removed to include build version
@Validated
public class AccountsController {


    IAccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

@Operation(description = "This Api is used for creating a customer as well as an account for the same customer",
summary = "Create Account and Customer Rest Api")

@ApiResponse( responseCode = "201",description = "HTTP Status 201 for record creation")
@PostMapping("/create")
public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto){

    accountsService.createAccount(customerDto);
return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
}

@Operation(summary = "Reads from db", description = "Api used for reading from the db")
@ApiResponse(responseCode = "302",
description = "Record was found")
@GetMapping("/read")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                           String mobileNumber){

        CustomerDto customerDto=accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerDto);

}
@Operation(summary = "Updates records in db",description = "API used to update the stored information based on customer and account ID")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Update was successful"),
        @ApiResponse(responseCode = "500", description = "Update was not successful")

})
@PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid CustomerDto customerDto)
{
    boolean updated = accountsService.updateAccount(customerDto);
    if(updated)
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
    else
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
}
    @Operation(summary = "Deletes data",description = "API used to delete the stored information based on customer mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deletion was successful"),

            @ApiResponse(responseCode = "417",
                    description = "Deletion was not successful",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )

    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Enter a valid mobile number")
                                                         String mobileNumber){
    boolean isDeleted = accountsService.deleteAccount(mobileNumber);
    if(isDeleted)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
    else
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));

    }
    @GetMapping("/version")
    public ResponseEntity<String> getVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }
}
