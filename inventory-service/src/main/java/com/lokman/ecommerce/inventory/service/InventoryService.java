package com.lokman.ecommerce.inventory.service;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.inventory.kafka.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
	
	public void processOrderEvent(OrderCreatedEvent event) {
		
	}
}
