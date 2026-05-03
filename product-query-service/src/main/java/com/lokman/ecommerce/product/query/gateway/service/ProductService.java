package com.lokman.ecommerce.product.query.gateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.query.external.client.ProductClient;
import com.lokman.ecommerce.product.query.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductClient productClient;

	@CircuitBreaker(name = "productService", fallbackMethod = "productsFallback")
	public List<ProductResponse> getProducts(int page, int limit) {

		return productClient.getProducts(page, limit);

	}

	public List<ProductResponse> productsFallback(int page, int limit, Throwable ex) {
		log.error("Product service unavailable", ex);
		return List.of();
	}
}
