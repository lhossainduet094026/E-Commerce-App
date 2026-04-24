package com.lokman.ecommerce.product.dto;

public record ProductResponse(Long id, 
		String name, 
		String description, 
		String skuCode) {}
