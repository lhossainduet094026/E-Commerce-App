package com.lokman.ecommerce.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
 
public record ProductRequest(@NotBlank(message = "SkuCode required") String skuCode, 
		@NotBlank(message = "Name required") String name, 
		String description, 
		@Min(value = 0, message = "Price cannot be negative") BigDecimal price, 
		Integer quantity) {
}
