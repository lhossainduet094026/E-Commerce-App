package com.lokman.ecommerce.product.query.external.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

	
}
