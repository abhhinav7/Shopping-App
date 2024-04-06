package com.HyperSrot.assignment.shopping_app.facade.impl;

import com.HyperSrot.assignment.shopping_app.entity.*;
import com.HyperSrot.assignment.shopping_app.facade.CouponFacade;
import com.HyperSrot.assignment.shopping_app.facade.OrderFacade;
import com.HyperSrot.assignment.shopping_app.facade.ProductFacade;
import com.HyperSrot.assignment.shopping_app.facade.UserFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.*;
import com.HyperSrot.assignment.shopping_app.service.OrderService;
import com.HyperSrot.assignment.shopping_app.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private CouponFacade couponFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransactionService transactionService;

    @Override
    @Transactional
    public ResponseEntity<?> placeOrder(Long userId, Long productId, int quantity, String code) {
        Product product = productFacade.findById(productId);
        Coupon tempCoupon = couponFacade.findCouponByCode(userId,code);
        User user = userFacade.findById(userId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WrongOrder("Product is Unavailable"));
        }
        if(quantity<1 || product.getQuantityAvailable()<quantity){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WrongOrder("Invalid quantity"));
        }
        if(!tempCoupon.isAvailability()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WrongOrder("Invalid coupon"));
        }

        product.setQuantityAvailable(product.getQuantityAvailable()-quantity);
        productFacade.save(product);

        tempCoupon.setAvailability(false);
        couponFacade.save(tempCoupon);

        double amount = (quantity * product.getPrice()*tempCoupon.getDiscountPercentage())/10;
        Orders order = new Orders();
        order.setOrderDate(LocalDate.now());
        order.setAmount(amount);
        order.setProduct(product);
        order.setUser(user);
        order.setQuantity(quantity);
        order.setCoupon(tempCoupon.getCode());
        Orders savedOrder = orderService.save(order);


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setCoupon(order.getCoupon());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setUserId(userId);
        return ResponseEntity.ok().body(orderDTO);

    }

    @Override
    public Orders findById(Long orderId) {
        return orderService.findById(orderId);
    }

    @Override
    public ResponseEntity<?> getOrder(Long userId, Long orderId) {
        Orders order = orderService.findById(orderId);
        if(order==null){
            WrongUserWithOrderIdDTO wrongUserWithOrderIdDTO = new WrongUserWithOrderIdDTO();
            wrongUserWithOrderIdDTO.setOrderId(orderId);
            wrongUserWithOrderIdDTO.setDescription("Order not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(wrongUserWithOrderIdDTO);
        }
        List<Transaction> transactionList = transactionService.findTransactionByUserIdAndOrderId(userId,orderId);
        List<UserWithOrderIdDTO> userWithOrderIdDTOList = new ArrayList<>();
        for (Transaction transaction:transactionList) {
            UserWithOrderIdDTO userWithOrderIdDTO = new UserWithOrderIdDTO();
            userWithOrderIdDTO.setAmount(order.getAmount());
            userWithOrderIdDTO.setCoupon(order.getCoupon());
            userWithOrderIdDTO.setDate(order.getOrderDate());
            userWithOrderIdDTO.setOrderId(order.getId());
            userWithOrderIdDTO.setTransactionId(transaction.getTransactionId());
            userWithOrderIdDTO.setStatus(transaction.getStatus());
            userWithOrderIdDTOList.add(userWithOrderIdDTO);
        }
        return ResponseEntity.ok(userWithOrderIdDTOList);
    }

    @Override
    public ResponseEntity<?> getOrders(Long userId) {
        List<Orders> orderList = orderService.findByUserId(userId);
        if(orderList==null || orderList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User has not ordered anything yet");
        }
        List<UserOrderDTO> userOrderDTOList = new ArrayList<>();
        for (Orders order:orderList){
            UserOrderDTO userOrderDTO = new UserOrderDTO();
            userOrderDTO.setAmount(order.getAmount());
            userOrderDTO.setCoupon(order.getCoupon());
            userOrderDTO.setOrderId(order.getId());
            userOrderDTO.setDate(order.getOrderDate());
            userOrderDTOList.add(userOrderDTO);
        }
        return ResponseEntity.ok(userOrderDTOList);
    }

    @Override
    public void save(Orders order) {
        orderService.save(order);
    }
}
