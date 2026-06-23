package com.vishalini.banking_service.repository;

import com.vishalini.banking_service.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
}
