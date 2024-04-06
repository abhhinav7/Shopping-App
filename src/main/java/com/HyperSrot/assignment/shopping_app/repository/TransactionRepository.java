package com.HyperSrot.assignment.shopping_app.repository;

import com.HyperSrot.assignment.shopping_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT * FROM Transaction t WHERE t.user_id = :userId AND t.order_id = :orderId AND t.status = 'successful'", nativeQuery = true)
    Optional<Transaction> findByUserIdAndOrderId(Long userId, Long orderId);

    @Query(value = "SELECT * FROM Transaction t WHERE t.user_id = :userId AND t.order_id = :orderId", nativeQuery = true)
    Optional<List<Transaction>> findTransactionByUserIdAndOrderId(Long userId, Long orderId);
}
