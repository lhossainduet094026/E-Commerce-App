package com.lokman.ecommerce.product.orchestrator.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lokman.ecommerce.product.orchestrator.dto.request.InventoryCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.InventoryUpdateResponse;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

	@PostMapping("/updateQuantity")
	ResponseEntity<InventoryUpdateResponse> upsertInventory(@RequestBody InventoryCreateRequest inventoryRequest);
}
