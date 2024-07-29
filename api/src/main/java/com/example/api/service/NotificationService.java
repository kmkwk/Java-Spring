package com.example.api.service;

import com.example.api.domain.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @EventListener
    @Async
    public void sendNotification(OrderCreateEvent orderCreateEvent) {
        System.out.println("알람 전송");
    }
}
