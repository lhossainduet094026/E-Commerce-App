package com.lokman.ecommerce.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic orderTopic() {
		return TopicBuilder.name("order-created-event")
				.partitions(4)
				.replicas(2)
				.build();
	}
}
