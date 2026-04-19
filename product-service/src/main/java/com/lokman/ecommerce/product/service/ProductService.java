package com.lokman.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lokman.ecommerce.product.dto.ProductRequest;
import com.lokman.ecommerce.product.dto.ProductResponse;
import com.lokman.ecommerce.product.exception.AlreadyExist;
import com.lokman.ecommerce.product.external.client.InventoryClient;
import com.lokman.ecommerce.product.external.dto.InventoryRequest;
import com.lokman.ecommerce.product.external.dto.InventoryResponse;
import com.lokman.ecommerce.product.model.Product;
import com.lokman.ecommerce.product.repository.ProductRepository;

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

		return new ProductResponse(savedProduct.getId(), 
				savedProduct.getName(), 
				savedProduct.getDescription(),
				savedProduct.getSkuCode(), 
				inventoryResponse.quantity());
	}

	private InventoryResponse upsertInventory(InventoryRequest inventoryRequest) {

		return inventoryClient.upsertInventory(inventoryRequest).getBody();
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
