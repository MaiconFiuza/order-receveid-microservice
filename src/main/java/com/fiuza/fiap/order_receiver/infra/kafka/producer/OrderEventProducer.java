package com.fiuza.fiap.order_receiver.infra.kafka.producer;

import com.fiuza.fiap.order_receiver.core.dto.OrderEventDTO;
import com.fiuza.fiap.order_receiver.core.dto.ProductBuyedDTO;
import com.fiuza.fiap.order_receiver.core.dto.TypeDTO;
import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.exceptions.InternalServerError;
import com.fiuza.fiap.order_receiver.core.gateway.OrderEventPublisherGateway;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer implements OrderEventPublisherGateway {

    private final KafkaTemplate<String, OrderEventDTO> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishOrderCreated(Order order, String status) {
        try {
            var products = order.getProducts()
                    .stream()
                    .map(product -> new ProductBuyedDTO(product.getSku(),product.getQuantity()))
                    .toList();
            var event = new OrderEventDTO(order.getId(),order.getCustomerId(), products, order.getPaymentType(),
                    TypeDTO.valueOf(status));
            kafkaTemplate.send("order-created", order.getId().toString(),event);

        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado, por favor tente novamente.");
        }

    }
}
