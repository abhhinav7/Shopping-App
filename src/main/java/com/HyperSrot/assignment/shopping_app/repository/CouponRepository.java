package com.HyperSrot.assignment.shopping_app.repository;

import com.HyperSrot.assignment.shopping_app.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByUserId(Long userId);

    @Query(value = "SELECT * FROM Coupon c WHERE c.user_id = :userId AND c.code = :code", nativeQuery = true)
    Coupon findCouponByUserIdOfCode(@Param("userId")Long userId,@Param("code") String code);
}
