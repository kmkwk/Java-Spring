package com.example.api.service;

import com.example.api.domain.OrderCreateEvent;
import com.example.api.domain.enums.NotificationType;
import com.example.api.strategy.NotificationStrategy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class NotificationService {

    private final Map<NotificationType, NotificationStrategy> strategyMap;

    public NotificationService(NotificationStrategy emailNotification, NotificationStrategy smsNotification) {
        strategyMap = new EnumMap<>(NotificationType.class);
        strategyMap.put(NotificationType.SMS, smsNotification);
        strategyMap.put(NotificationType.EMAIL, emailNotification);
    }

    @EventListener
    @Async
    public void sendNotification(OrderCreateEvent orderCreateEvent) {
        System.out.println("알람 전송");
    }

    public String notify(NotificationType platform, String message, Long userId) {
        NotificationStrategy notificationStrategy = strategyMap.get(platform);

        return notificationStrategy.sendNotification(message, userId);
    }
}
