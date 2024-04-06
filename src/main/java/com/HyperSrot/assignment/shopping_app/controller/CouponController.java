package com.HyperSrot.assignment.shopping_app.controller;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.facade.CouponFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.FetchCouponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetchCoupons")
public class CouponController {

    @Autowired
    private CouponFacade couponFacade;

    @GetMapping("/{userId}")
    public List<FetchCouponDTO> getCoupon(@PathVariable Long userId){
        return couponFacade.findCoupon(userId);
    }
}
