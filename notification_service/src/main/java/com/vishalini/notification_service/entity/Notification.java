package com.vishalini.notification_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String customerName;
    private String email;
    private String eventType;
    private Double amount;
    private String status;
    @CreationTimestamp
    private String  createdAt;
}
