package com.fiuza.fiap.order_receiver.infra.adapter;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.enums.Status;
import com.fiuza.fiap.order_receiver.core.exceptions.InternalServerError;
import com.fiuza.fiap.order_receiver.core.gateway.OrderGateway;
import com.fiuza.fiap.order_receiver.infra.mappers.OrderMapper;
import com.fiuza.fiap.order_receiver.infra.models.OrderModel;
import com.fiuza.fiap.order_receiver.infra.models.ProductBuyedModel;
import com.fiuza.fiap.order_receiver.infra.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImp implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderRepositoryImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        try {
            OrderModel orderModel = new OrderModel(
                    order.getId(),
                    order.getCustomerId(),
                    order.getPaymentId(),
                    Status.valueOf(order.getStatus().name()),
                    new ArrayList<>(),
                    order.getPaymentType()
            );

            OrderModel savedOrder = orderRepository.save(orderModel);
            List<ProductBuyedModel> productModels = order.getProducts().stream()
                    .map(p -> new ProductBuyedModel(
                            p.getId(),
                            p.getSku(),
                            p.getQuantity(),
                            savedOrder
                    )).collect(Collectors.toCollection(ArrayList::new));

            savedOrder.setProducts(productModels);

            OrderModel updatedOrder = orderRepository.save(savedOrder);

            Order mappedOrder = OrderMapper.orderModelToOrder(updatedOrder);

            return mappedOrder;
        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado, por favor tente novamente.");
        }
    }
}