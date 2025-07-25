package com.fiuza.fiap.order_receiver.core.dto;

public record ProductBuyedDTO(
        Long sku,
        Integer quantity
) {
}
