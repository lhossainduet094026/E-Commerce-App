package com.lokman.ecommerce.inventory.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.inventory.kafka.event.OrderCreatedEvent;
import com.lokman.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventConsumer {

	private final InventoryService inventoryService;

	@KafkaListener(topics = "${kafka.topics.order-events}")
	public void consume(@Payload OrderCreatedEvent event, 
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_PARTITION) int partition, 
			@Header(KafkaHeaders.OFFSET) long offset,
			Acknowledgment ack) {

		log.info("Received [correlationId={}, orderId={}, items = {}, offset={}]", event.correlationId(),
				event.orderId(), event.items(), offset);

		// validate the request
		if (event == null || event.userId() == null || event.orderId() == null || event.items() == null
				|| event.items().size() <= 0) {
			log.error("Invalid event payload — acking to skip [offset={}]", offset);
			ack.acknowledge(); // bad message: ack so it won't loop
			return;
		}

		try {

			inventoryService.processOrderEvent(event); 
			ack.acknowledge(); 

		} catch (Exception e) {
			log.error("Processing failed for orderId={}: {}", event.orderId(), e.getMessage(), e);
			throw e; // let Spring retry → DLQ kick in
		}
	}
}
