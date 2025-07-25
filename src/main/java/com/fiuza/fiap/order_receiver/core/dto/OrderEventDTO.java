package com.fiuza.fiap.order_receiver.core.dto;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.entities.ProductBuyed;
import com.fiuza.fiap.order_receiver.core.enums.Status;

import java.util.List;
import java.util.UUID;

public record OrderEventDTO(
        UUID orderId,
        UUID customerId,
        List<ProductBuyedDTO>products,
        String cardNumber,
        TypeDTO type
) {
    public Order toOrder() {
        String paymentType = (cardNumber == null) ? "pix" : cardNumber;
        var products = this.products
                .stream()
                .map(it -> new ProductBuyed(it.sku(),it.quantity())).toList();
        return new Order(this.customerId, Status.ABERTO, products, paymentType);
    }
}
