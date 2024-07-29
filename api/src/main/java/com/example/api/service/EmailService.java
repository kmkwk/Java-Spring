package com.example.api.service;

import com.example.api.domain.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    @EventListener
    @Async
    public void sendEmail(OrderCreateEvent orderCreateEvent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("이메일 전송");
    }
}

