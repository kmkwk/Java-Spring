package com.example.api.adapter;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CouponReadAdapter implements CouponPort {

    private final CouponRepository couponRepository;


    @Override
    public int saveAll(List<Coupon> coupons) {
        return 0;
    }

    @Override
    public Coupon findById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new NoSuchElementException("등록된 쿠폰이 없습니다."));
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }
}
