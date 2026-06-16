package com.vishalini.banking_service.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AccountRequest {
    private String customerName;
    private String email;
    private String mobile;
    private Double balance;
}
