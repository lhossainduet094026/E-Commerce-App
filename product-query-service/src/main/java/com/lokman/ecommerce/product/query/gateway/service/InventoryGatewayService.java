package com.lokman.ecommerce.product.query.gateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.query.external.client.InventoryClient;
import com.lokman.ecommerce.product.query.response.InventoryResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryGatewayService {

	private final InventoryClient inventoryClient;

	@Retry(name = "inventoryService")
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
	public List<InventoryResponse> getInventories(List<String> skuCodes) {

		return inventoryClient.getInventories(skuCodes);
	}

	public List<InventoryResponse> inventoryFallback(List<String> skuCodes, Throwable ex) {

		log.error("Inventory service failed. Returning default response", ex);

		return skuCodes.stream().map(code -> new InventoryResponse(code, 0)).toList();
	}
}
