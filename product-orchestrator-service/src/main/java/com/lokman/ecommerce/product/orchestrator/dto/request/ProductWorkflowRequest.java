package com.lokman.ecommerce.product.orchestrator.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ProductWorkflowRequest(@NotBlank(message = "SkuCode required") String skuCode, 
		@NotBlank(message = "Name required") String name, 
		String description, 
		@DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
		Integer quantity) {

}
