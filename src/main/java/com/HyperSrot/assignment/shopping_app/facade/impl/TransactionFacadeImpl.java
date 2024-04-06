package com.HyperSrot.assignment.shopping_app.facade.impl;

import com.HyperSrot.assignment.shopping_app.entity.Orders;
import com.HyperSrot.assignment.shopping_app.entity.Transaction;
import com.HyperSrot.assignment.shopping_app.facade.OrderFacade;
import com.HyperSrot.assignment.shopping_app.facade.TransactionFacade;
import com.HyperSrot.assignment.shopping_app.facade.UserFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.TransactionDTO;
import com.HyperSrot.assignment.shopping_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TransactionFacadeImpl implements TransactionFacade {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private TransactionService transactionService;

    private static final String PREFIX = "tran";
    private static final AtomicLong counter = new AtomicLong(1);

    public static String generateTransactionId() {
        return String.format("%s%09d", PREFIX, counter.getAndIncrement());
    }

    private static boolean generatePaymentStatus(){
        //logic for Bank Payment
        return false;
    }
    private static boolean generateServerResponse(){
        //Logic for Server Issue
        return false;
    }

    @Override
    public ResponseEntity<?> addTransaction(Long userId, Long orderId, double amount) {
        Orders order = orderFacade.findById(orderId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionId(generateTransactionId());
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setOrderId(orderId);
        transactionDTO.setUserId(userId);
        if(order==null){
            transaction.setDescription("Payment Failed due to invalid order id");
            transaction.setStatus("failed");
            transactionService.save(transaction);
            transactionDTO.setStatus("failed");
            return ResponseEntity.badRequest().body(transactionDTO);
        }

        transaction.setOrder(order);
        transaction.setUser(order.getUser());

        if(amount!=order.getAmount()){
            transaction.setDescription("Payment Failed as amount is invalid");
            transaction.setStatus("failed");
            transactionService.save(transaction);
            transactionDTO.setStatus("failed");
            return ResponseEntity.badRequest().body(transactionDTO);
        }
        Transaction tempTransaction = transactionService.findByUserIdAndOrderId(userId,orderId);
        if(tempTransaction!=null){
            transaction.setDescription("Order is already paid for");
            transaction.setStatus("failed");
            transactionService.save(transaction);
            transactionDTO.setStatus("failed");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(transactionDTO);
        }

        if(generatePaymentStatus()){
            transaction.setDescription("Payment Failed from bank");
            transaction.setStatus("failed");
            transactionService.save(transaction);
            transactionDTO.setStatus("failed");
            return ResponseEntity.badRequest().body(transactionDTO);
        }

        if(generateServerResponse()){
            transaction.setDescription("No response from payment server");
            transaction.setStatus("failed");
            transactionService.save(transaction);
            transactionDTO.setStatus("failed");
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(transactionDTO);
        }

        transaction.setDescription("Your transaction was a success");
        transaction.setStatus("successful");
        transactionService.save(transaction);
        transactionDTO.setStatus("successful");
        return ResponseEntity.ok(transactionDTO);

    }


}
