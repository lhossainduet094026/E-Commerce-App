package com.lokman.ecommerce.product.query.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lokman.ecommerce.product.query.response.InventoryResponse;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

	@PostMapping("/batch")
	List<InventoryResponse> getInventories(@RequestBody List<String> skuCodes);
}
