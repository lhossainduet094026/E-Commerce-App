package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.request.InventoryCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.InventoryUpdateResponse;
import com.lokman.ecommerce.product.orchestrator.external.client.InventoryClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class InventoryService {

	private final InventoryClient inventoryClient;

	@CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
	@Retry(name = "inventoryService")
	public InventoryUpdateResponse upsertInventory(InventoryCreateRequest inventoryCreateRequest) {

		ResponseEntity<InventoryUpdateResponse> response = inventoryClient.upsertInventory(inventoryCreateRequest);

		if (response.getBody() == null) {

			return new InventoryUpdateResponse(null, 
					inventoryCreateRequest.skuCode(),
					inventoryCreateRequest.quantity(), 
					"FAILED");
		}

		return response.getBody();
	}

	public InventoryUpdateResponse inventoryFallback(InventoryCreateRequest request, Exception ex) {
		// log + return safe response or trigger compensation later
		return new InventoryUpdateResponse(null, request.skuCode(), request.quantity(), "FAILED");
	}

}
