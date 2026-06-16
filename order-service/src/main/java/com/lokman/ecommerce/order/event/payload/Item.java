package com.lokman.ecommerce.order.event.payload;

public record Item(Long productId, String skuCode, int quantity) {
}
