package com.mulei.blisscart.dto;

import com.mulei.blisscart.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderResponseDTO {
    private String referenceNumber;
    private LocalDateTime orderTime;
    private OrderStatus status;
    private Double total;

    // Constructors
    public OrderResponseDTO(String referenceNumber, LocalDateTime orderTime, OrderStatus status, Double total) {
        this.referenceNumber = referenceNumber;
        this.orderTime = orderTime;
        this.status = status;
        this.total = total;
    }

    // Getters and Setters
    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
