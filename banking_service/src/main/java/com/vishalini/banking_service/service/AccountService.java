package com.vishalini.banking_service.service;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
     public AccountRepository accountRepository;
    public Account createAccount(AccountRequest accountRequest){
          Account a = new Account();
          a.setCustomerName(accountRequest.getCustomerName());
          a.setEmail(accountRequest.getEmail());
          a.setBalance(accountRequest.getBalance());
          a.setMobile(accountRequest.getMobile());
          a.setAccountNumber(String.valueOf(
                  System.currentTimeMillis()));
           return accountRepository.save(a);
    }

    public Account getAccount(){
        Account a1 = new Account();
        List<Account> accountRepositoryAll = accountRepository.findAll();
        for(Account a:accountRepositoryAll){
            a1.setAccountNumber(a.getAccountNumber());
            a1.setBalance(a.getBalance());
            a1.setMobile(a.getMobile());
            a1.setEmail(a.getEmail());
        }
        return a1;
    }
}
