package com.HyperSrot.assignment.shopping_app.facade;

import org.springframework.http.ResponseEntity;

public interface TransactionFacade {
    ResponseEntity<?> addTransaction(Long userId, Long orderId, double amount);
}
