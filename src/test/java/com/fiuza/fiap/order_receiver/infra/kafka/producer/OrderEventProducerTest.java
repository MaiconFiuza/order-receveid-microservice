package com.fiuza.fiap.order_receiver.infra.kafka.producer;

import com.fiuza.fiap.order_receiver.core.dto.OrderEventDTO;
import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.exceptions.InternalServerError;
import com.fiuza.fiap.order_receiver.helpers.OrderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderEventProducerTest {

    @Mock
    private KafkaTemplate<String, OrderEventDTO> kafkaTemplate;

    @InjectMocks
    private OrderEventProducer orderEventProducer;

    private Order order;

    @BeforeEach
    void setup() {
        order = OrderHelper.createOrderWithId(UUID.randomUUID());
    }

    @Test
    void publishOrderCreated_shouldSendKafkaMessage() {
        when(kafkaTemplate.send(anyString(), anyString(), any(OrderEventDTO.class)))
                .thenReturn(null);

        String status = "ABERTO";

        orderEventProducer.publishOrderCreated(order, status);

        ArgumentCaptor<OrderEventDTO> eventCaptor = ArgumentCaptor.forClass(OrderEventDTO.class);

        verify(kafkaTemplate, times(1)).send(
                eq("order-created"),
                eq(order.getId().toString()),
                eventCaptor.capture()
        );

        OrderEventDTO capturedEvent = eventCaptor.getValue();

        assertThat(capturedEvent.orderId()).isEqualTo(order.getId());
        assertThat(capturedEvent.customerId()).isEqualTo(order.getCustomerId());
    }

    @Test
    void publishOrderCreated_shouldThrowInternalServerErrorOnException() {
        when(kafkaTemplate.send(anyString(), anyString(), any(OrderEventDTO.class)))
                .thenThrow(new RuntimeException("Erro no Kafka"));

        String status = "ABERTO";

        InternalServerError exception = Assertions.assertThrows(InternalServerError.class, () -> {
            orderEventProducer.publishOrderCreated(order, status);
        });

        assertThat(exception).hasMessage("Aconteceu um erro inesperado, por favor tente novamente.");
    }
}
