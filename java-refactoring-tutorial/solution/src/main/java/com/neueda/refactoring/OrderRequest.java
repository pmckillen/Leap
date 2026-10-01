package com.neueda.refactoring;

import java.util.List;

public record OrderRequest(
        CustomerTier tier,
        List<Item> items,
        String countryCode,
        boolean expressDelivery,
        String promoCode) {

    public OrderRequest {
        items = List.copyOf(items);
    }
}
