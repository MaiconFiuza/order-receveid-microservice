package com.fiuza.fiap.order_receiver.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.fiap.order_receiver.application.controllers.OrderController;
import com.fiuza.fiap.order_receiver.core.dto.OrderEventDTO;
import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.usecases.CreateOrderUseCase;
import com.fiuza.fiap.order_receiver.helpers.OrderEventDTOHelper;
import com.fiuza.fiap.order_receiver.helpers.OrderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderEventDTO orderEventDTO;
    private Order orderResponse;

    @BeforeEach
    void setup() {
        orderEventDTO = OrderEventDTOHelper.createSample();
        orderResponse = OrderHelper.createOrderWithId(orderEventDTO.orderId());
    }

    @Test
    void create_shouldReturnCreatedOrder() throws Exception {
        when(createOrderUseCase.execute(any(Order.class), anyString()))
                .thenReturn(orderResponse);

        mockMvc.perform(post("/order-received/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderEventDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(orderResponse.getId().toString()));
    }
}
