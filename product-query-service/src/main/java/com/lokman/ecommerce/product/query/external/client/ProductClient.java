package com.lokman.ecommerce.product.query.external.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {

	
}
