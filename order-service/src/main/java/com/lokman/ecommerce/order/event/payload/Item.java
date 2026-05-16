package com.lokman.ecommerce.order.event.payload;

public record Item(Long productId, int quantity) {
}
