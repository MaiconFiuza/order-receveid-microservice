package com.fiuza.fiap.order_receiver.helpers;

import com.fiuza.fiap.order_receiver.core.entities.Order;
import com.fiuza.fiap.order_receiver.core.entities.ProductBuyed;
import com.fiuza.fiap.order_receiver.core.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderHelper {
    public static Order createOrderDefault() {
        UUID customerId = UUID.randomUUID();
        List<ProductBuyed> products = new ArrayList<>();
        products.add(ProductBuyedHelper.createProductBuyedDefault());

        return new Order(
                UUID.randomUUID(),
                null,
                customerId,
                Status.ABERTO,
                products,
                "pix"
        );
    }

    public static List<ProductBuyed> createProductBuyedModelList() {
        List<ProductBuyed> products = new ArrayList<>();

        UUID orderId = UUID.randomUUID();
        Order parentOrder = new Order();
        parentOrder.setId(orderId);

        products.add(new ProductBuyed(
                UUID.randomUUID(),
                1001L,
                5,
                parentOrder
        ));

        products.add(new ProductBuyed(
                UUID.randomUUID(),
                1002L,
                10,
                parentOrder
        ));

        return products;
    }

    public static Order createOrderWithId(UUID id) {
        UUID customerId = UUID.randomUUID();
        List<ProductBuyed> products = new ArrayList<>();
        products.add(ProductBuyedHelper.createProductBuyedDefault());

        return new Order(
                id,
                UUID.randomUUID(),
                customerId,
                Status.ABERTO,
                products,
                "pix"
        );
    }
}
