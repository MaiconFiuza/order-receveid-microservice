package com.fiuza.fiap.order_receiver.core.entities;

import com.fiuza.fiap.order_receiver.core.enums.Status;

import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID customerId;
    private UUID paymentId = null;
    private Status status;
    private List<ProductBuyed> products;
    private String paymentType;

    public Order(UUID id, UUID paymentId, UUID customerId, Status status,List<ProductBuyed> products, String paymentType) {
        this.id = id;
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.status = status;
        this.products = products;
        this.paymentType = paymentType;
    }

    public Order(UUID customerId, UUID paymentId,Status status, List<ProductBuyed> products, String paymentType) {
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.status = status;
        this.products = products;
        this.paymentType = paymentType;
    }

    public Order(UUID customerId, Status status,List<ProductBuyed> products,String paymentType ) {
        this.customerId = customerId;
        this.status = status;
        this.products = products;
        this.paymentType = paymentType;

    }

    public Order() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ProductBuyed> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBuyed> products) {
        this.products = products;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
