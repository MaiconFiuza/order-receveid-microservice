package com.fiuza.fiap.order_receiver.infra.repository;

import com.fiuza.fiap.order_receiver.infra.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
}
