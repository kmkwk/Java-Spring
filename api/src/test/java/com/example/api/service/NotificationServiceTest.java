package com.example.api.service;

import com.example.api.domain.enums.NotificationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationServiceTest {


    @Autowired
    private NotificationService notificationService;


    @Test
    public void 알람_전략_패턴() {
        String message = "메시지";
        Long userId = 1L;
        NotificationType platform = NotificationType.SMS;
        String smsMessage = String.format("SMS: %s", message);

        String notify = notificationService.notify(platform, message, userId);

        assertThat(notify).isEqualTo(smsMessage);
    }
}