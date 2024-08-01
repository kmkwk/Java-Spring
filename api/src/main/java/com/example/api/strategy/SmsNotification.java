package com.example.api.strategy;

import org.springframework.stereotype.Component;

@Component
public class SmsNotification implements NotificationStrategy{
    @Override
    public String sendNotification(String message, Long userId) {
        String smsMessage = String.format("SMS: %s", message);

        System.out.println(smsMessage);

        return smsMessage;
    }
}
