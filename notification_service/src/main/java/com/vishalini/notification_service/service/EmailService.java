package com.vishalini.notification_service.service;


import com.vishalini.notification_service.dto.NotificationEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    public void sendTransactional(NotificationEvent event) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(event.getEmail());
        helper.setSubject("Bank Transaction Alert");
        helper.setText(buildHtmlBody(event),true);
        javaMailSender.send(mimeMessage);
        System.out.println("Email Sent Successful");
    }

    private String buildHtmlBody(NotificationEvent notification) {

        return """
            <html>

            <body style="font-family:Arial">

            <h2 style="color:#0d6efd;">
                🏦 ABC Bank
            </h2>

            <p>Dear %s,</p>

            <p>
            Your transaction has been processed successfully.
            </p>

            <table border="1"
                   cellpadding="10"
                   cellspacing="0">

                <tr>

                    <th>Transaction</th>

                    <td>%s</td>

                </tr>

                <tr>

                    <th>Amount</th>

                    <td>₹ %.2f</td>

                </tr>

                <tr>

                    <th>Account</th>

                    <td>%s</td>

                </tr>

            </table>

            <br>

            Regards,

            <br>

            ABC Bank

            </body>

            </html>

            """.formatted(
                notification.getCustomerName(),
                notification.getEventType(),
                notification.getAmount(),
                notification.getAccountNumber()

        );
    }
}
