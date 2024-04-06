package com.HyperSrot.assignment.shopping_app.facade;

import com.HyperSrot.assignment.shopping_app.entity.Orders;
import org.springframework.http.ResponseEntity;

public interface OrderFacade {
    ResponseEntity<?> placeOrder(Long userId, Long productId, int quantity, String code);

    Orders findById(Long orderId);

    ResponseEntity<?> getOrder(Long userId, Long orderId);

    void save(Orders orders);

    ResponseEntity<?> getOrders(Long userId);
}
