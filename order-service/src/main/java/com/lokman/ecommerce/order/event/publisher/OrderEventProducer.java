package com.lokman.ecommerce.order.event.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.order.event.payload.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventProducer {

	private final KafkaTemplate<Object, Object> kafkaTemplate;

	@Value("${kafka.topics.order-events}")
	private String orderEventsTopic;

	public void send(OrderCreatedEvent event) {

		kafkaTemplate.send(orderEventsTopic, event.orderId(), event)
		.whenComplete((result, ex) -> {

			if (ex != null) {
				log.error("Failed to publish OrderCreatedEvent. orderId={}, correlationId={}",
						event.orderId(), 
						event.correlationId(), 
						ex);
			} else {
				log.info("OrderCreatedEvent published successfully. orderId={}, partition={}, offset={}",
						event.orderId(), 
						result.getRecordMetadata().partition(), 
						result.getRecordMetadata().offset());
			}
		});

	}
}
