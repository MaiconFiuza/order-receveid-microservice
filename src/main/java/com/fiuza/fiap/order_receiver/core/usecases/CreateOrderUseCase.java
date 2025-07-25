package com.fiuza.fiap.order_receiver.core.usecases;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.gateway.OrderEventPublisherGateway;
import com.fiuza.fiap.order_receiver.core.gateway.OrderGateway;

public class CreateOrderUseCase {
    private final OrderGateway orderGateway;
    private final OrderEventPublisherGateway orderEventPublisherGateway;

    public CreateOrderUseCase(OrderGateway orderGateway, OrderEventPublisherGateway orderEventPublisherGateway) {
        this.orderGateway = orderGateway;
        this.orderEventPublisherGateway = orderEventPublisherGateway;
    }

    public Order execute(Order order, String should) {
        var savedOder = orderGateway.create(order);
        orderEventPublisherGateway.publishOrderCreated(savedOder,should);
        return savedOder;
    }
}
