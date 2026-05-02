package com.lokman.ecommerce.product.query.response;

public record ProductWithInventoryResponse(Long id, 
		String name, 
		String description, 
		String skuCode, 
		Integer quantity) {}
