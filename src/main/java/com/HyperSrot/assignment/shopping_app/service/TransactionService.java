package com.HyperSrot.assignment.shopping_app.service;

import com.HyperSrot.assignment.shopping_app.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findByUserIdAndOrderId(Long userId, Long orderId);

    List<Transaction> findTransactionByUserIdAndOrderId(Long userId, Long orderId);

    Transaction save(Transaction transaction);
}
