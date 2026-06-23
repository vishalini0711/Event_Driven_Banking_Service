package com.vishalini.notification_service.consumer;

import com.vishalini.notification_service.config.KafkaConsumerConfig;
import com.vishalini.notification_service.dto.NotificationEvent;
import com.vishalini.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationConsumer {
   private final NotificationService notificationService;

    @KafkaListener(topics = "banking-notification-topic", groupId = "notification-group")
    public void consume(NotificationEvent event){
        log.info("Received event : {} ", event);
        notificationService.saveNotification(event);
    }
}
