package com.vishalini.notification_service.service;

import com.vishalini.notification_service.dto.NotificationEvent;
import com.vishalini.notification_service.entity.Notification;
import com.vishalini.notification_service.enumPackage.NotificationClass;
import com.vishalini.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    public NotificationRepository notificationRepository;
    public final EmailService emailService;
    public void saveNotification(NotificationEvent event){
        Notification notification = new Notification();
        notification.setAmount(event.getAmount());
        notification.setEmail(event.getEmail());
        notification.setAccountNumber(event.getAccountNumber());
        notification.setCustomerName(event.getCustomerName());
        notification.setEventType(event.getEventType());
        try {
            emailService.sendTransactional(event);
            notification.setStatus(NotificationClass.SUCCESS);

        } catch (Exception e) {
            notification.setStatus(NotificationClass.FAILED);
            log.error("Email sending failed", e);
        }
        notificationRepository.save(notification);
    }
}
