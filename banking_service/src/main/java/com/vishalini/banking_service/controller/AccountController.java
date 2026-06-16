package com.vishalini.banking_service.controller;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.dto.DepositRequest;
import com.vishalini.banking_service.dto.WithdrawRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private  AccountService accountService;
    @PostMapping
    public Account createAccount(@RequestBody AccountRequest request){
            return accountService.createAccount(request);
    }
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }
    @PostMapping("/deposit")
    public Account depositAmount(@RequestBody DepositRequest depositRequest){
        return accountService.depositAmount(depositRequest);
    }
    @PostMapping("/withdraw")
    public Account withdrawAmount(@RequestBody WithdrawRequest withdrawRequest){
        return accountService.withdrawAmount(withdrawRequest);
    }
}
