package com.fiuza.fiap.order_receiver.core.entities;

import java.util.UUID;

public class ProductBuyed {
    private UUID id;
    private Long sku;
    private Integer quantity;
    private Order order;

    public ProductBuyed(UUID id, Long sku, Integer quantity) {
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
    }

    public ProductBuyed(Long sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public ProductBuyed(UUID id, Long sku, Integer quantity, Order order) {
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
