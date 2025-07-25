package com.fiuza.fiap.order_receiver.application.controllers;

import com.fiuza.fiap.order_receiver.core.dto.OrderEventDTO;
import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.usecases.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-received")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody OrderEventDTO request) {
        var response = createOrderUseCase.execute(request.toOrder(),request.type().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
