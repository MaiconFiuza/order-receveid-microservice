package com.fiuza.fiap.order_receiver.core.usecases;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.gateway.OrderEventPublisherGateway;
import com.fiuza.fiap.order_receiver.core.gateway.OrderGateway;
import com.fiuza.fiap.order_receiver.helpers.OrderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private OrderEventPublisherGateway orderEventPublisherGateway;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    private Order order;

    @BeforeEach
    void setup() {
        order = OrderHelper.createOrderWithId(UUID.randomUUID());
    }

    @Test
    void execute_shouldCreateOrderAndPublishEvent() {
        when(orderGateway.create(any(Order.class))).thenReturn(order);

        String should = "some-flag-or-context";
        Order result = createOrderUseCase.execute(order, should);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(order.getId());

        verify(orderGateway, times(1)).create(order);

        verify(orderEventPublisherGateway, times(1)).publishOrderCreated(order, should);
    }

    @Test
    void execute_whenOrderGatewayThrowsException_shouldPropagate() {
        when(orderGateway.create(any(Order.class))).thenThrow(new RuntimeException("Erro simulado"));

        String should = "some-flag";

        Assertions.assertThrows(RuntimeException.class, () -> {
            createOrderUseCase.execute(order, should);
        });

        verify(orderEventPublisherGateway, never()).publishOrderCreated(any(), any());
    }
}
