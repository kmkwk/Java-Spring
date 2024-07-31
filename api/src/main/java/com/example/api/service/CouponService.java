package com.example.api.service;


import com.example.api.adapter.CouponPort;
import com.example.api.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {
    private final CouponPort couponMapper;

    private final CouponPort couponRepository;

    public CouponService(@Qualifier("couponCreateAdapter") CouponPort couponMapper,
                         @Qualifier("couponReadAdapter") CouponPort couponRepository) {
        this.couponMapper = couponMapper;
        this.couponRepository = couponRepository;
    }

    public int createCoupon(Long userId) {
        Coupon coupon = Coupon.builder().userId(userId).build();

        return couponMapper.saveAll(List.of(coupon));
    }

    public List<Coupon> findAllCoupon() {
        return couponRepository.findAll();
    }

}
