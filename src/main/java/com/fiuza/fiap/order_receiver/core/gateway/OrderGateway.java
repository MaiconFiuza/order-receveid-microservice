package com.fiuza.fiap.order_receiver.core.gateway;

import com.fiuza.fiap.order_receiver.core.entities.Order;

public interface OrderGateway {
    Order create(Order order);
}
