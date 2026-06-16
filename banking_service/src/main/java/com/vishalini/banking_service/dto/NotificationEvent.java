package com.vishalini.banking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private String accountNumber;
    private String customerName;
    private String email;
    private String eventType;
    private Double amount;
}
