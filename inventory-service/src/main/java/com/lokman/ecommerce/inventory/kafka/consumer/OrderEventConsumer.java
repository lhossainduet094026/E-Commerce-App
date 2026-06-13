package com.lokman.ecommerce.inventory.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.inventory.kafka.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventConsumer {

	@KafkaListener(topics = "${kafka.topics.order-events}", groupId = "${kafka.consumer.group-id}")
	public void consume(OrderCreatedEvent event) {

		log.info("Received order event {}", event.orderId());
	}
}
