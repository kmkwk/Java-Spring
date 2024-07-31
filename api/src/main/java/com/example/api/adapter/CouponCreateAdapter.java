package com.example.api.adapter;

import com.example.api.domain.Coupon;
import com.example.api.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponCreateAdapter implements CouponPort {

    private final CouponMapper couponMapper;

    @Override
    public int saveAll(List<Coupon> coupons) {
        return couponMapper.saveAll(coupons);
    }

    @Override
    public Coupon findById(Long couponId) {
        return null;
    }

    @Override
    public List<Coupon> findAll() {
        return null;
    }
}
