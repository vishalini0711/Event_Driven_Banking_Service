package com.vishalini.banking_service.service;

import com.vishalini.banking_service.dto.AccountRequest;
import com.vishalini.banking_service.dto.DepositRequest;
import com.vishalini.banking_service.dto.NotificationEvent;
import com.vishalini.banking_service.dto.WithdrawRequest;
import com.vishalini.banking_service.entity.Account;
import com.vishalini.banking_service.exception.AccountNotFoundException;
import com.vishalini.banking_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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
            a1.setId(a.getId());
            a1.setCustomerName(a.getCustomerName());
        }
        return a1;
    }

    public Account depositAmount(DepositRequest depositRequest){
        Account account = accountRepository
                .findByAccountNumber(depositRequest.getAccount())
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));
        double amount = account.getBalance() + depositRequest.getAmount();
        account.setBalance(amount);
        accountRepository.save(account);
        NotificationEvent event = new NotificationEvent(account.getAccountNumber(),account.getCustomerName()
        ,account.getEmail(),"DEPOSIT",depositRequest.getAmount());
        log.info("Publishing Event : {}", event);
        kafkaTemplate.send("banking-notification-topic",event);
        return account;
    }

    public Account withdrawAmount(WithdrawRequest withdrawRequest){
        Account account = accountRepository.findByAccountNumber(withdrawRequest.getAccount()).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        if(account.getBalance()<withdrawRequest.getAmount()){
            throw new RuntimeException("Insufficent Balance");
        }
        account.setBalance(account.getBalance()-withdrawRequest.getAmount());
        accountRepository.save(account);
        NotificationEvent event = new NotificationEvent(account.getAccountNumber(),account.getCustomerName()
                ,account.getEmail(),"WITHDRAW",withdrawRequest.getAmount());
        log.info("Publishing Event : {}", event);
        kafkaTemplate.send("banking-notification-topic",event);
        return account;
    }
}
