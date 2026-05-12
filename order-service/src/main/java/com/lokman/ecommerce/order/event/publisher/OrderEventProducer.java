package com.lokman.ecommerce.order.event.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.order.event.payload.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

	private final KafkaTemplate<Object, Object> kafkaTemplate;
	
	public void send(OrderCreatedEvent event) {
//        kafkaTemplate.send("order-events", event.orderId().toString(), event);
    
		kafkaTemplate.send("order-events", key, data);
	}
	
}
