package com.vishalini.banking_service.controller;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private  AccountService accountService;
    @PostMapping("/account")
    public Account createAccount(@RequestBody AccountRequest request){
            return accountService.createAccount(request);
    }
}
