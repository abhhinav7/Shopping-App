package com.HyperSrot.assignment.shopping_app.facade.impl;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.entity.User;
import com.HyperSrot.assignment.shopping_app.facade.CouponFacade;
import com.HyperSrot.assignment.shopping_app.facade.UserFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.FetchCouponDTO;
import com.HyperSrot.assignment.shopping_app.service.CouponService;
import com.HyperSrot.assignment.shopping_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CouponFacadeImpl implements CouponFacade {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @Override
    public void save(Coupon coupon) {
        couponService.save(coupon);
    }

    @Override
    public List<FetchCouponDTO> findCoupon(Long userId) {

        List<Coupon> couponList = couponService.findCouponByUserId(userId);
        List<FetchCouponDTO>  fetchCouponDTOList = new ArrayList<>();
        for(Coupon coupon:couponList){
            FetchCouponDTO fetchCouponDTO = new FetchCouponDTO();
            fetchCouponDTO.setCode(coupon.getCode());
            fetchCouponDTOList.add(fetchCouponDTO);
        }
        return fetchCouponDTOList;
    }

    @Override
    public Coupon findCouponByCode(Long userId, String code) {
        return couponService.findCouponByUserIdOfCode(userId,code);
    }
}
