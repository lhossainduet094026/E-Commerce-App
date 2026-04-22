package com.lokman.ecommerce.product.orchestrator.dto.response;

public record ProductWorkflowResponse(Long id, 
		String name, 
		String description, 
		String skuCode, 
		Integer quantity) {}
