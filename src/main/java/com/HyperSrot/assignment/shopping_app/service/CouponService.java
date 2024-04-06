package com.HyperSrot.assignment.shopping_app.service;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.entity.User;

import java.util.List;

public interface CouponService {
    void save(Coupon coupon);

    List<Coupon> findCouponByUserId(Long userId);

    Coupon findCouponByUserIdOfCode(Long userId, String code);
}
