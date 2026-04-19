package com.lokman.ecommerce.product.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lokman.ecommerce.product.external.dto.InventoryRequest;
import com.lokman.ecommerce.product.external.dto.InventoryResponse;

@FeignClient(name = "inventory-client", url = "${}")
public interface InventoryClient {

	@PostMapping("/updateQuantity")
	ResponseEntity<InventoryResponse> upsertInventory(@RequestBody InventoryRequest inventoryRequest);
}
