package com.lokman.ecommerce.product.query.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.ecommerce.product.query.response.ProductWithInventoryResponse;
import com.lokman.ecommerce.product.query.service.ProductQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductQueryController {

	private final ProductQueryService productQueryService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductWithInventoryResponse> getProductList(@RequestParam(name = "page") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {

		return productQueryService.getProductList(page, limit);
	}
}
