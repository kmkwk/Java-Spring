package com.example.api.adapter;

import com.example.api.domain.Coupon;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CouponPort {

    int saveAll(List<Coupon> coupons);

    Coupon findById(Long couponId);

    List<Coupon> findAll();

}
