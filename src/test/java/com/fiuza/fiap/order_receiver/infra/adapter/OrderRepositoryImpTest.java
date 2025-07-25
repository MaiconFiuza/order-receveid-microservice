package com.fiuza.fiap.order_receiver.infra.adapter;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.enums.Status;
import com.fiuza.fiap.order_receiver.core.exceptions.InternalServerError;
import com.fiuza.fiap.order_receiver.helpers.OrderHelper;
import com.fiuza.fiap.order_receiver.infra.models.OrderModel;
import com.fiuza.fiap.order_receiver.infra.models.ProductBuyedModel;
import com.fiuza.fiap.order_receiver.infra.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryImpTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderRepositoryImp orderRepositoryImp;

    private UUID orderId;
    private Order order;

    @BeforeEach
    void setup() {
        orderId = UUID.randomUUID();
        order = OrderHelper.createOrderWithId(orderId);
    }

    @Test
    void create_success() {
        OrderModel savedOrderWithoutProducts = new OrderModel(
                order.getId(),
                order.getCustomerId(),
                order.getPaymentId(),
                Status.valueOf(order.getStatus().name()),
                new ArrayList<>(),
                order.getPaymentType()
        );

        List<ProductBuyedModel> productModels = order.getProducts().stream()
                .map(p -> new ProductBuyedModel(p.getId(), p.getSku(), p.getQuantity(), null))
                .collect(Collectors.toList());

        OrderModel savedOrderWithProducts = new OrderModel(
                order.getId(),
                order.getCustomerId(),
                order.getPaymentId(),
                Status.valueOf(order.getStatus().name()),
                productModels,
                order.getPaymentType()
        );

        doReturn(savedOrderWithoutProducts)
                .doReturn(savedOrderWithProducts)
                .when(orderRepository).save(any(OrderModel.class));

        Order savedOrder = orderRepositoryImp.create(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isEqualTo(order.getId());

        verify(orderRepository, times(2)).save(any(OrderModel.class));
    }

    @Test
    void create_failure_throwsInternalServerError() {
        UUID orderId = UUID.randomUUID();
        Order order = OrderHelper.createOrderWithId(orderId);

        doThrow(new RuntimeException("Erro inesperado no banco"))
                .when(orderRepository).save(any());

        InternalServerError thrown = assertThrows(
                InternalServerError.class,
                () -> orderRepositoryImp.create(order)
        );

        assertThat(thrown).hasMessage("Aconteceu um erro inesperado, por favor tente novamente.");

        verify(orderRepository).save(any());
    }
}
