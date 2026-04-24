package com.lokman.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lokman.ecommerce.product.dto.ProductRequest;
import com.lokman.ecommerce.product.dto.ProductResponse;
import com.lokman.ecommerce.product.exception.AlreadyExistException;
import com.lokman.ecommerce.product.model.Product;
import com.lokman.ecommerce.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional
	public ProductResponse createProduct(ProductRequest productRequest) {

		// create product
		boolean existsBySkuCode = productRepository.existsBySkuCode(productRequest.skuCode());

		if (existsBySkuCode)
			throw new AlreadyExistException("Product already exist with skuCode " + productRequest.skuCode());

		Product product = new Product();
		product.setName(productRequest.name());
		product.setDescription(productRequest.description());
		product.setPrice(productRequest.price());
		product.setSkuCode(productRequest.skuCode());

		product = productRepository.save(product);
		log.info("Product created successfully: {}", product);

		return new ProductResponse(product.getId(), 
				product.getName(), 
				product.getDescription(),
				product.getSkuCode());
	}
}
