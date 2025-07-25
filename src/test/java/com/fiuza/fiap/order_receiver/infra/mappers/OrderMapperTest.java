package com.fiuza.fiap.order_receiver.infra.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.entities.ProductBuyed;
import com.fiuza.fiap.order_receiver.core.enums.Status;
import com.fiuza.fiap.order_receiver.helpers.OrderHelper;
import com.fiuza.fiap.order_receiver.helpers.OrderModelHelper;
import com.fiuza.fiap.order_receiver.infra.models.OrderModel;
import com.fiuza.fiap.order_receiver.infra.models.ProductBuyedModel;
import org.junit.jupiter.api.Test;

public class OrderMapperTest {

    @Test
    void orderToOrderModel_shouldMapCorrectly() {
        UUID orderId = UUID.randomUUID();

        Order order = OrderHelper.createOrderWithId(orderId);

        OrderModel orderModel = OrderMapper.orderToOrderModel(order);

        assertThat(orderModel).isNotNull();
        assertThat(orderModel.getId()).isEqualTo(order.getId());
        assertThat(orderModel.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderModel.getPaymentId()).isEqualTo(order.getPaymentId());
        assertThat(orderModel.getStatus().name()).isEqualTo(order.getStatus().name());
        assertThat(orderModel.getPaymentType()).isEqualTo(order.getPaymentType());

        assertThat(orderModel.getProducts()).hasSize(order.getProducts().size());

        for (int i = 0; i < order.getProducts().size(); i++) {
            ProductBuyed originalProduct = order.getProducts().get(i);
            ProductBuyedModel mappedProduct = orderModel.getProducts().get(i);

            assertThat(mappedProduct.getId()).isEqualTo(originalProduct.getId());
            assertThat(mappedProduct.getSku()).isEqualTo(originalProduct.getSku());
            assertThat(mappedProduct.getQuantity()).isEqualTo(originalProduct.getQuantity());

            assertThat(mappedProduct.getOrder().getId()).isEqualTo(orderModel.getId());
        }
    }

    @Test
    void orderModelToOrder_shouldMapCorrectly() {
        UUID orderId = UUID.randomUUID();

        List<ProductBuyed> productBuyedModels = OrderHelper.createProductBuyedModelList();

        OrderModel orderModel = OrderModelHelper.createOrderModelWithId(
                orderId,
                UUID.randomUUID(),
                UUID.randomUUID(),
                Status.ABERTO,
                productBuyedModels,
                "CREDIT_CARD"
        );

        Order order = OrderMapper.orderModelToOrder(orderModel);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(orderModel.getId());
        assertThat(order.getCustomerId()).isEqualTo(orderModel.getCustomerId());
        assertThat(order.getPaymentId()).isEqualTo(orderModel.getPaymentId());
        assertThat(order.getStatus().name()).isEqualTo(orderModel.getStatus().name());
        assertThat(order.getPaymentType()).isEqualTo(orderModel.getPaymentType());

        assertThat(order.getProducts()).hasSize(orderModel.getProducts().size());

        for (int i = 0; i < orderModel.getProducts().size(); i++) {
            ProductBuyedModel originalProductModel = orderModel.getProducts().get(i);
            ProductBuyed mappedProduct = order.getProducts().get(i);

            assertThat(mappedProduct.getId()).isEqualTo(originalProductModel.getId());
            assertThat(mappedProduct.getSku()).isEqualTo(originalProductModel.getSku());
            assertThat(mappedProduct.getQuantity()).isEqualTo(originalProductModel.getQuantity());

            assertThat(mappedProduct.getOrder().getId()).isEqualTo(order.getId());
        }
    }
}
