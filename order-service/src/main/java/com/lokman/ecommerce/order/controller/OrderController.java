package com.lokman.ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.ecommerce.order.request.CreateOrderRequest;
import com.lokman.ecommerce.order.response.OrderResponse;
import com.lokman.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	private final OrderService orderService;

	public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
		OrderResponse orderResponse = orderService.createOrder(createOrderRequest);

		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
	}
}
