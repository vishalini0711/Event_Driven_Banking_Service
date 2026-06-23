package com.vishalini.notification_service.service;

import com.vishalini.notification_service.dto.NotificationEvent;
import com.vishalini.notification_service.entity.Notification;
import com.vishalini.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    public NotificationRepository notificationRepository;
    public final EmailService emailService;
    public void saveNotification(NotificationEvent event){
        Notification notification = new Notification();
        notification.setAmount(event.getAmount());
        notification.setEmail(event.getEmail());
        notification.setStatus("SUCCESS");
        notification.setAccountNumber(event.getAccountNumber());
        notification.setCustomerName(event.getCustomerName());
        notification.setEventType(event.getEventType());
        notificationRepository.save(notification);
        emailService.sendTransactional(event);
    }
}
