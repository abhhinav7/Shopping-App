package com.HyperSrot.assignment.shopping_app.service.implementation;

import com.HyperSrot.assignment.shopping_app.entity.Transaction;
import com.HyperSrot.assignment.shopping_app.repository.TransactionRepository;
import com.HyperSrot.assignment.shopping_app.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction findByUserIdAndOrderId(Long userId, Long orderId) {
        Optional<Transaction> optionalTransaction = transactionRepository.findByUserIdAndOrderId(userId,orderId);
        if(optionalTransaction.isPresent()){
            return optionalTransaction.get();
        }
        return null;
    }

    @Override
    public List<Transaction> findTransactionByUserIdAndOrderId(Long userId, Long orderId) {
        Optional<List<Transaction>> optionalTransaction = transactionRepository.findTransactionByUserIdAndOrderId(userId,orderId);
        if(optionalTransaction.isPresent()){
            return optionalTransaction.get();
        }
        return null;
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
