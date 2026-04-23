package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductResponse;
import com.lokman.ecommerce.product.orchestrator.external.client.ProductClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

	private final ProductClient productClient;
	
	public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
		
		ResponseEntity<ProductResponse> response = productClient.createProduct(productCreateRequest);
		
		return response.getBody();
	}
}
