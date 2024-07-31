package com.example.api.mapper;

import com.example.api.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {

    int saveAll(List<Coupon> coupons);

}
