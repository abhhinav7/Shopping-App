package com.HyperSrot.assignment.shopping_app.repository;

import com.HyperSrot.assignment.shopping_app.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    Optional<List<Orders>> findByUserId(Long userId);
}
