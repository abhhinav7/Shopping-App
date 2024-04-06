package com.HyperSrot.assignment.shopping_app.facade;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.responsedto.FetchCouponDTO;

import java.util.List;

public interface CouponFacade {
    void save(Coupon coupon);

    List<FetchCouponDTO> findCoupon(Long userId);

    Coupon findCouponByCode(Long userId, String code);
}
