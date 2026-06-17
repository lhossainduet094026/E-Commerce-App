package com.lokman.ecommerce.inventory.kafka.event;

public record ProductItem(Long productId, String skuCode, int quantity) {}
