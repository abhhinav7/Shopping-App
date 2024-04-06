package com.HyperSrot.assignment.shopping_app.facade.impl;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import com.HyperSrot.assignment.shopping_app.entity.User;
import com.HyperSrot.assignment.shopping_app.facade.CouponFacade;
import com.HyperSrot.assignment.shopping_app.facade.UserFacade;
import com.HyperSrot.assignment.shopping_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private CouponFacade couponFacade;

    @Override
    public User save(User user) {
        User tempUser = userService.save(user);

        //5% off on a user
        Coupon couponFirst = new Coupon();
        couponFirst.setUser(tempUser);
        couponFirst.setCode("OFF5");
        couponFirst.setDiscountPercentage(5);
        couponFirst.setAvailability(true);
        couponFacade.save(couponFirst);

        //10% off on a user
        Coupon couponSecond = new Coupon();
        couponSecond.setUser(tempUser);
        couponSecond.setCode("OFF10");
        couponSecond.setDiscountPercentage(10);
        couponFirst.setAvailability(true);
        couponFacade.save(couponSecond);

        return tempUser;
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        if(findById(id)!=null){
            userService.deleteUserById(id);
            return ResponseEntity.accepted().body("Successfully Deleted The User With Id:" + id);
        }
        return ResponseEntity.badRequest().body("User With Id: " + id + " Does Not Exist");
    }
}
