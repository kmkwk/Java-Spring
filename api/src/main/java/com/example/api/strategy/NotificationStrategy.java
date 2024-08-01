package com.example.api.strategy;


public interface NotificationStrategy {

    String sendNotification(String message, Long userId);
}
