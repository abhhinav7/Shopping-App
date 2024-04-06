package com.HyperSrot.assignment.shopping_app.service.implementation;

import com.HyperSrot.assignment.shopping_app.entity.Orders;
import com.HyperSrot.assignment.shopping_app.repository.OrderRepository;
import com.HyperSrot.assignment.shopping_app.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Orders findById(Long orderId) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        return null;
    }

    @Override
    public List<Orders> findByUserId(Long userId) {
        Optional<List<Orders>> optionalOrder = orderRepository.findByUserId(userId);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        return null;
    }
}
