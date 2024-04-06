package com.HyperSrot.assignment.shopping_app.controller;

import com.HyperSrot.assignment.shopping_app.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping("/{userId}/{productId}/order")
    public ResponseEntity<?> placeOrder(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @RequestParam("qty") int quantity,
            @RequestParam("coupon") String code) {
        return orderFacade.placeOrder(userId,productId,quantity,code);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrders(@PathVariable("userId") Long userId){
        return  orderFacade.getOrders(userId);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> getOrder(
            @PathVariable("userId") Long userId,
            @PathVariable("orderId") Long orderId
    ){
        return  orderFacade.getOrder(userId,orderId);
    }
}
