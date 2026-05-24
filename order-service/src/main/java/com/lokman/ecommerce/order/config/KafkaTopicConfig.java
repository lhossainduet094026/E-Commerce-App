package com.lokman.ecommerce.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {

	@Value("${kafka.topics.order-events}")
	private String orderEventsTopic;
	
	@Bean
	public NewTopic orderTopic() {
		return TopicBuilder.name(orderEventsTopic)
				.partitions(4)
				.replicas(2)
				.config("retention.ms", "604800000") // 7 days
				.build();
	}
}
