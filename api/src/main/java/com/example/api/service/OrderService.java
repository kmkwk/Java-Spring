package com.example.api.service;

import com.example.api.domain.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;

    public void createOrder(Long orderId) {
        System.out.println("주문 생성");

        OrderCreateEvent orderCreateEvent = OrderCreateEvent.builder().orderId(orderId).build();

        eventPublisher.publishEvent(orderCreateEvent);

        System.out.println("주문 완료");
    }
}
