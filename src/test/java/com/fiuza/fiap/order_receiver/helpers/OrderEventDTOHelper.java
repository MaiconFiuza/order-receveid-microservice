package com.fiuza.fiap.order_receiver.helpers;

import com.fiuza.fiap.order_receiver.core.dto.OrderEventDTO;
import com.fiuza.fiap.order_receiver.core.dto.ProductBuyedDTO;
import com.fiuza.fiap.order_receiver.core.dto.TypeDTO;

import java.util.List;
import java.util.UUID;

public class OrderEventDTOHelper {

    public static OrderEventDTO createSample() {
        UUID orderId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        ProductBuyedDTO product = new ProductBuyedDTO(12345L, 2);

        List<ProductBuyedDTO> products = List.of(product);

        String paymentType = "CREDIT_CARD";

        TypeDTO type = TypeDTO.ABERTO;

        return new OrderEventDTO(orderId, customerId, products, paymentType, type);
    }
}
