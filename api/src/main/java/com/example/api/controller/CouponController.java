package com.example.api.controller;

import com.example.api.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;


    @PostMapping("/{userId}")
    public ResponseEntity<Object> createCoupon(@PathVariable Long userId) {
        couponService.createCoupon(userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
