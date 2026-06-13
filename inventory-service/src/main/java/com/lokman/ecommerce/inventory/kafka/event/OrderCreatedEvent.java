package com.lokman.ecommerce.inventory.kafka.event;

import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(Long orderId,
        Long userId,
        List<ProductItem> items,
        Instant timestamp,
        String correlationId) {
	
}
