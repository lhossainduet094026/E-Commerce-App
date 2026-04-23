package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.request.InventoryCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.InventoryUpdateResponse;
import com.lokman.ecommerce.product.orchestrator.external.client.InventoryClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class InventoryService {

	private final InventoryClient inventoryClient;

	public InventoryUpdateResponse upsertInventory(InventoryCreateRequest inventoryCreateRequest) {

		ResponseEntity<InventoryUpdateResponse> response = inventoryClient.upsertInventory(inventoryCreateRequest);
		
		return response.getBody();
	}

}
