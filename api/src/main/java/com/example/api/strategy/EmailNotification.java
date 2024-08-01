package com.example.api.strategy;

import org.springframework.stereotype.Component;

@Component
public class EmailNotification implements NotificationStrategy{
    @Override
    public String sendNotification(String message, Long userId) {
        String emailMessage = String.format("이메일: %s", message);

        System.out.println(emailMessage);

        return emailMessage;
    }
}
