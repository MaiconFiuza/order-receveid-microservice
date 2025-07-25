package com.fiuza.fiap.order_receiver.core.dto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderEventDTOTest {
    @Test
    void test_orderEventDTO_creation_and_accessors() {
        UUID orderId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        List<ProductBuyedDTO> products = List.of(new ProductBuyedDTO(1L, 2));
        String cardNumber = "1234567890123456";
        TypeDTO type = TypeDTO.FECHADO_COM_SUCESSO;

        OrderEventDTO dto = new OrderEventDTO(orderId, customerId, products, cardNumber, type);

        assertEquals(orderId, dto.orderId());
        assertEquals(customerId, dto.customerId());
        assertEquals(products, dto.products());
        assertEquals(cardNumber, dto.cardNumber());
        assertEquals(type, dto.type());
    }
}
