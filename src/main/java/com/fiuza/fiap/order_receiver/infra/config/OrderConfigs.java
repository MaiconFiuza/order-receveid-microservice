package com.fiuza.fiap.order_receiver.infra.config;

import com.fiuza.fiap.order_receiver.core.gateway.OrderEventPublisherGateway;
import com.fiuza.fiap.order_receiver.core.gateway.OrderGateway;
import com.fiuza.fiap.order_receiver.core.usecases.CreateOrderUseCase;
import com.fiuza.fiap.order_receiver.infra.adapter.OrderRepositoryImp;
import com.fiuza.fiap.order_receiver.infra.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigs {

    @Bean
    public OrderGateway orderGateway(OrderRepository orderRepository) {
        return new OrderRepositoryImp(orderRepository);
    }


    @Bean
    public CreateOrderUseCase createOrderUseCase(
            OrderGateway orderGateway,
            OrderEventPublisherGateway orderEventPublisherGateway
    ) {
        return new CreateOrderUseCase(orderGateway, orderEventPublisherGateway);
    }
}
