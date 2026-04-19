package com.lokman.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lokman.ecommerce.product.dto.ProductRequest;
import com.lokman.ecommerce.product.dto.ProductResponse;
import com.lokman.ecommerce.product.exception.AlreadyExist;
import com.lokman.ecommerce.product.external.dto.InventoryRequest;
import com.lokman.ecommerce.product.model.Product;
import com.lokman.ecommerce.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse createProduct(ProductRequest productRequest) {

		// create product
		createProductEntity(productRequest);
		// update inventory

		upsertInventory(new InventoryRequest(productRequest.getSkuCode(), 1).get;
	}

	private void upsertInventory(InventoryRequest inventoryRequest) {

		
	}

	@Transactional
	public void createProductEntity(ProductRequest productRequest) {

		boolean existsBySkuCode = productRepository.existsBySkuCode(productRequest.getSkuCode());

		if (existsBySkuCode)
			throw new AlreadyExist("Product already exist with skuCode" + productRequest.getSkuCode());

		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setSkuCode(productRequest.getSkuCode());

		productRepository.save(product);
		log.info("Product created successfully: {}", product);
	}
}
