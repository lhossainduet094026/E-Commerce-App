package com.lokman.ecommerce.inventory.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.DeserializationException;

import com.lokman.ecommerce.inventory.kafka.event.OrderCreatedEvent;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Bean
	public DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, Object> template) {
//		// Now we create the recoverer and give it the rule book
		return new DeadLetterPublishingRecoverer(template, this::resolveDLQDestination);
	}

	private TopicPartition resolveDLQDestination(ConsumerRecord<?, ?> record, Exception exception) {
		String dlqTopic = record.topic() + ".DLT";
		return new TopicPartition(dlqTopic, record.partition());
	}

	@Bean
	public DefaultErrorHandler handler(DeadLetterPublishingRecoverer recoverer) {

		DefaultErrorHandler handler = new DefaultErrorHandler(recoverer);

		handler.addNotRetryableExceptions(DeserializationException.class);

		return handler;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderCreatedEvent> kafkaListenerContainerFactory(
			DefaultErrorHandler errorHandler, 
			ConsumerFactory<String, OrderCreatedEvent> cf) {
		
		var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderCreatedEvent>();
		factory.setConsumerFactory(cf);
		factory.setCommonErrorHandler(errorHandler);
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}
}
