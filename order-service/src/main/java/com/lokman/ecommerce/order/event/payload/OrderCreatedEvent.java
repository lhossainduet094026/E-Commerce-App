package com.lokman.ecommerce.order.event.payload;

import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(Long orderId,
        Long userId,
        List<Item> items,
        Instant timestamp,
        String correlationId) {
	
}
