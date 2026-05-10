package com.lokman.ecommerce.order.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
		@NotNull(message = "User id is required")
		Long userId,

		@NotEmpty(message = "Order items can not be empty!") 
		@Valid
		List<OrderItemRequest> items) {}
