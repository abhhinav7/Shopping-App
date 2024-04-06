package com.HyperSrot.assignment.shopping_app.service.implementation;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.entity.User;
import com.HyperSrot.assignment.shopping_app.repository.CouponRepository;
import com.HyperSrot.assignment.shopping_app.service.CouponService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponRepository couponRepository;

    @Override
    @Transactional
    public void save(Coupon coupon) {
        couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findCouponByUserId(Long userId) {
        return couponRepository.findByUserId(userId);
    }

    @Override
    public Coupon findCouponByUserIdOfCode(Long userId, String code) {
        return couponRepository.findCouponByUserIdOfCode(userId,code);
    }
}
