package com.HyperSrot.assignment.shopping_app.controller;

import com.HyperSrot.assignment.shopping_app.facade.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<?> addTransaction(
            @PathVariable Long userId,
            @PathVariable Long orderId,
            @RequestParam double amount
    ){
        return transactionFacade.addTransaction(userId,orderId,amount);
    }
}
