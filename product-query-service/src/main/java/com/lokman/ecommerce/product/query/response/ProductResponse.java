package com.lokman.ecommerce.product.query.response;

public record ProductResponse(Long id, 
		String name, 
		String description, 
		String skuCode) {
}
