package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductResponse;
import com.lokman.ecommerce.product.orchestrator.exception.ProductCreationException;
import com.lokman.ecommerce.product.orchestrator.external.client.ProductClient;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

	private final ProductClient productClient;

	public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {

		try {

			ResponseEntity<ProductResponse> response = productClient.createProduct(productCreateRequest);

			if (response == null || response.getBody() == null) {
				throw new ProductCreationException("Empty response from product service");
			}

			return response.getBody();

		} catch (FeignException e) {
			log.error("Product service failed: {}", e.getMessage());
			throw new ProductCreationException("Product service call failed", e);
		}
	}

	public void deleteProduct(Long id) {

		
	}
}
