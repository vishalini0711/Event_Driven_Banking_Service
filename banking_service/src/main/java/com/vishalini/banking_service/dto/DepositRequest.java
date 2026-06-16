package com.vishalini.banking_service.dto;

import lombok.Data;

@Data
public class DepositRequest {
    private String account;
    private Double amount;
}
