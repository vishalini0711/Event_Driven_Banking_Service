package com.vishalini.banking_service.controller;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.dto.DepositRequest;
import com.vishalini.banking_service.dto.WithdrawRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts Api's", description = "Bank Account Management")
public class AccountController {
    @Autowired
    private  AccountService accountService;
    @PostMapping
    @Operation(summary = "Create Account", description = "Create new Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account Creation Successful"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    public Account createAccount(@RequestBody AccountRequest request){
            return accountService.createAccount(request);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get Account By Id", description = "Get the account by id")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }
    @PostMapping("/deposit")
    @Operation(summary = "Deposit Amount", description = "Deposit money into existing Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deposit Successful"),
            @ApiResponse(responseCode = "404", description = "Account Not Found"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    public Account depositAmount(@RequestBody DepositRequest depositRequest){
        return accountService.depositAmount(depositRequest);
    }
    @PostMapping("/withdraw")
    @Operation(summary = "Withdraw Amount", description = "Withdraw money from existing Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Withdrawn Successful"),
            @ApiResponse(responseCode = "404", description = "Account Not Found"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    public Account withdrawAmount(@RequestBody WithdrawRequest withdrawRequest){
        return accountService.withdrawAmount(withdrawRequest);
    }
}
