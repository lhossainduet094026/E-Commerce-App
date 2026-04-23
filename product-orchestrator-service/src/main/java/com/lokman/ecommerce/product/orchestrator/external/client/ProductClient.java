package com.lokman.ecommerce.product.orchestrator.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductResponse;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {

	@PostMapping
	ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest);
}
