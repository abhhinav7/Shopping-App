package com.HyperSrot.assignment.shopping_app.responsedto;

import java.time.LocalDate;

public class UserWithOrderIdDTO {
    private Long orderId;
    private double amount;
    private LocalDate date;
    private String Coupon;
    private String transactionId;
    private String Status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String coupon) {
        Coupon = coupon;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
