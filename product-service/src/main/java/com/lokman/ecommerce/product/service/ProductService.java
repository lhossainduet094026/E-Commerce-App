package com.lokman.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lokman.ecommerce.product.dto.ProductRequest;
import com.lokman.ecommerce.product.dto.ProductResponse;
import com.lokman.ecommerce.product.exception.AlreadyExist;
import com.lokman.ecommerce.product.exception.InventoryFailedException;
import com.lokman.ecommerce.product.external.client.InventoryClient;
import com.lokman.ecommerce.product.external.dto.InventoryRequest;
import com.lokman.ecommerce.product.external.dto.InventoryResponse;
import com.lokman.ecommerce.product.model.Product;
import com.lokman.ecommerce.product.repository.ProductRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	private final InventoryClient inventoryClient;

	public ProductResponse createProduct(ProductRequest productRequest) {

		// create product
		Product savedProduct = createProductEntity(productRequest);

		// update inventory
		InventoryResponse inventoryResponse = upsertInventory(new InventoryRequest(productRequest.skuCode(), productRequest.quantity()));

		if (inventoryResponse == null || inventoryResponse.quantity() == 0) {
			// compensate product
			log.warn("Inventory unavailable/invalid → compensating product skuCode={}", savedProduct.getSkuCode());

			productRepository.deleteById(savedProduct.getId());

			throw new InventoryFailedException("Inventory unavailable, product rolled back");
		}

		return new ProductResponse(savedProduct.getId(), 
				savedProduct.getName(), 
				savedProduct.getDescription(),
				savedProduct.getSkuCode(), 
				inventoryResponse.quantity());
	}

	@Retry(name = "inventoryService")
	@CircuitBreaker(name = "inventoryService" , fallbackMethod = "inventoryFallback")
	public InventoryResponse upsertInventory(InventoryRequest inventoryRequest) {

		InventoryResponse response = inventoryClient.upsertInventory(inventoryRequest).getBody();

		if (response == null) {
		    log.warn("Inventory returned NULL response for sku={}", inventoryRequest.skuCode());
		}

		return response;
	}

	private InventoryResponse inventoryFallback(InventoryRequest request, Throwable t) {

		 log.error("Inventory service failed. skuCode={}", request.skuCode(), t);

	    return new InventoryResponse(null, request.skuCode(), 0);
	}
	
	@Transactional
	public Product createProductEntity(ProductRequest productRequest) {

		boolean existsBySkuCode = productRepository.existsBySkuCode(productRequest.skuCode());

		if (existsBySkuCode)
			throw new AlreadyExist("Product already exist with skuCode " + productRequest.skuCode());

		Product product = new Product();
		product.setName(productRequest.name());
		product.setDescription(productRequest.description());
		product.setPrice(productRequest.price());
		product.setSkuCode(productRequest.skuCode());

		product = productRepository.save(product);
		log.info("Product created successfully: {}", product);
		return product;
	}
}
