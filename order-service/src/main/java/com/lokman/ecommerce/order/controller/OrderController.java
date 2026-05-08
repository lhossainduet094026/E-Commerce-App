package com.lokman.ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.ecommerce.order.request.ProductOrderRequest;
import com.lokman.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	private final OrderService orderService;
	
	@ResponseStatus(HttpStatus.OK)
	public ResponseStatus createOrder(@RequestBody ProductOrderRequest productOrderRequest) {
		return orderService.createOrder(productOrderRequest);
	}
}
