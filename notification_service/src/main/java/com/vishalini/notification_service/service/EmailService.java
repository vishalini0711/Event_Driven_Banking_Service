package com.vishalini.notification_service.service;


import com.vishalini.notification_service.dto.NotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    public void sendTransactional(NotificationEvent event){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(event.getEmail());
        simpleMailMessage.setSubject("Bank Transaction Alert");
        simpleMailMessage.setText("Hello " + event.getCustomerName() + ",\n\n" +
                "A " + event.getEventType() + " transaction of ₹" +
                event.getAmount() + " has been processed successfully.\n\n" +
                "Thank you,\nBanking Team");
        log.info("Before sending email");
        javaMailSender.send(simpleMailMessage);
        System.out.println("Email Sent Successful");
    }
}
