package com.lokman.ecommerce.order.request;

public record ProductOrderRequest(Long productId, Long userId, int quantity) {}
