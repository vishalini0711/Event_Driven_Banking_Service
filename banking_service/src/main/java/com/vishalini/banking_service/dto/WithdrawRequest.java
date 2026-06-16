package com.vishalini.banking_service.dto;

import lombok.Data;
@Data
public class WithdrawRequest {
        private String account;
        private Double amount;
}
