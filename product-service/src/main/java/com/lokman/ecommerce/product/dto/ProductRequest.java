package com.lokman.ecommerce.product.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

	private String skuCode;

	private String name;

	private String description;

	private BigDecimal price;
}
