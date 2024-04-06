package com.HyperSrot.assignment.shopping_app.entity;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    private double amount;

    private String status; // "successful", "failed"
    private String description; // Additional details in case of failure

    public Transaction(User user, Orders order, double amount, String transactionId, String status, String description) {
        this.user = user;
        this.order = order;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.description = description;
    }

    public Transaction() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

