package com.HyperSrot.assignment.shopping_app.service;

import com.HyperSrot.assignment.shopping_app.entity.Orders;

import java.util.List;

public interface OrderService {
    Orders save(Orders order);

    Orders findById(Long orderId);

    List<Orders> findByUserId(Long userId);
}
