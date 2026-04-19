package com.lokman.ecommerce.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.ecommerce.product.dto.ProductRequest;
import com.lokman.ecommerce.product.dto.ProductResponse;
import com.lokman.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductController {

	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}

}
