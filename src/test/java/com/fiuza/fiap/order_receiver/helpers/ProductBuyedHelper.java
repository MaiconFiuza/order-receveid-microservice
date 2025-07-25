package com.fiuza.fiap.order_receiver.helpers;

import com.fiuza.fiap.order_receiver.core.entities.ProductBuyed;

import java.util.UUID;

public class ProductBuyedHelper {
    public static ProductBuyed createProductBuyedDefault() {
        return new ProductBuyed(
                UUID.randomUUID(),
                1L,
                5,
                null
        );
    }

    public static ProductBuyed createProductBuyedWithSkuAndQuantity(Long sku, Integer quantity) {
        return new ProductBuyed(
                UUID.randomUUID(),
                sku,
                quantity,
                null
        );
    }
}
