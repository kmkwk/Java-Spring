package com.example.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void 이벤트_리스너() {
        Long orderId = 1L;

        orderService.createOrder(orderId);

    }
}