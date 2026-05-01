package com.lokman.ecommerce.product.query.service;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.query.external.client.ProductClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductQueryService {

	private final ProductClient productClient;
	private final InventoryClient inventoryClient;
	
	
}
