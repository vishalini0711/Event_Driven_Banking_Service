package com.vishalini.banking_service.service;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.dto.DepositRequest;
import com.vishalini.banking_service.dto.NotificationEvent;
import com.vishalini.banking_service.dto.WithdrawRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
     public AccountRepository accountRepository;
    private final KafkaTemplate<String,
            NotificationEvent> kafkaTemplate;
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

    public Account getAccount(Long id){
        Account a1 = new Account();
        List<Account> accountRepositoryAllById = accountRepository.findAllById(id);
        for(Account a:accountRepositoryAllById){
            a1.setAccountNumber(a.getAccountNumber());
            a1.setBalance(a.getBalance());
            a1.setMobile(a.getMobile());
            a1.setEmail(a.getEmail());
        }
        return a1;
    }

    public Account depositAmount(DepositRequest depositRequest){
        Account repositoryByAccountNumber = accountRepository.findByAccountNumber(depositRequest.getAccount());
        double amount = repositoryByAccountNumber.getBalance() + depositRequest.getAmount();
        Account a = new Account();
        a.setBalance(amount);
        accountRepository.save(a);
        NotificationEvent event = new NotificationEvent(a.getAccountNumber(),a.getCustomerName()
        ,a.getEmail(),"DEPOSIT",depositRequest.getAmount());
        kafkaTemplate.send("banking-notification-topic",event);
        return a;
    }

    public Account withdrawAmount(WithdrawRequest withdrawRequest){
        Account byAccountNumber = accountRepository.findByAccountNumber(withdrawRequest.getAccount());
        if(byAccountNumber.getBalance()<withdrawRequest.getAmount()){
            throw new RuntimeException("Insufficent Balance");
        }
        Account a = new Account();
        a.setBalance(byAccountNumber.getBalance()-withdrawRequest.getAmount());
        accountRepository.save(a);
        return a;
    }
}
