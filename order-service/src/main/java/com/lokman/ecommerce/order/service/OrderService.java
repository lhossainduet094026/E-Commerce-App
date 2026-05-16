package com.lokman.ecommerce.order.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.order.event.payload.Item;
import com.lokman.ecommerce.order.event.payload.OrderCreatedEvent;
import com.lokman.ecommerce.order.event.publisher.OrderEventProducer;
import com.lokman.ecommerce.order.external.ProductClient;
import com.lokman.ecommerce.order.mapper.OrderMapper;
import com.lokman.ecommerce.order.model.Order;
import com.lokman.ecommerce.order.repository.OrderRepository;
import com.lokman.ecommerce.order.request.CreateOrderRequest;
import com.lokman.ecommerce.order.request.OrderItemRequest;
import com.lokman.ecommerce.order.response.OrderResponse;
import com.lokman.ecommerce.order.response.ProductResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

	private final ProductClient productClient;
	private final OrderRepository orderRepository;
	private final OrderEventProducer orderEventProducer;

	public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {

		String correlationId = UUID.randomUUID().toString();

		List<ProductResponse> products = fetchProductPrice(correlationId, createOrderRequest.items());

		Map<Long, BigDecimal> mapOfProductPrice = products.stream()
				.collect(Collectors.toMap(ProductResponse::productId, ProductResponse::price));

		Order order = storeOrder(createOrderRequest, mapOfProductPrice);

		if (order.getId() == null) {
			return new OrderResponse(null, order.getStatus().FAILED.name(), "Order creation failed");
		}

		OrderCreatedEvent preparedOrderCreatedEvent = prepareOrderCreatedEvent(order.getId(), correlationId, createOrderRequest);

		orderEventProducer.send(preparedOrderCreatedEvent);

		return new OrderResponse(order.getId(), order.getStatus().name(), "Order created");
	}

	private OrderCreatedEvent prepareOrderCreatedEvent(Long orderId, String correlationId,
			CreateOrderRequest createOrderRequest) {

		List<Item> items = createOrderRequest.items().stream().map(item -> new Item(item.productId(), item.quantity()))
				.toList();

		return new OrderCreatedEvent(orderId, createOrderRequest.userId(), items, Instant.now(), correlationId);
	}

	@Transactional
	public Order storeOrder(CreateOrderRequest createOrderRequest, Map<Long, BigDecimal> mapOfProductPrice) {
		
		Order preparedOrder = prepareOrder(createOrderRequest, mapOfProductPrice);

		return orderRepository.save(preparedOrder);
	}

	private Order prepareOrder(CreateOrderRequest createOrderRequest, Map<Long, BigDecimal> mapOfProductPrice) {

		Order order = OrderMapper.toOrder(createOrderRequest, mapOfProductPrice);
		order.setTotalPrice(calculateTotalPrice(createOrderRequest.items(), mapOfProductPrice));

		return order;
	}

	private List<ProductResponse> fetchProductPrice(String correlationId, List<OrderItemRequest> items) {
		List<Long> productIds = items.stream().map(OrderItemRequest::productId).toList();
		log.info("CorrelationId={}", correlationId);
		return productClient.getProducts(correlationId, productIds);
	}

	private BigDecimal calculateTotalPrice(List<OrderItemRequest> items, Map<Long, BigDecimal> mapOfProductPrice) {

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (OrderItemRequest item : items) {
			BigDecimal price = mapOfProductPrice.get(item.productId());
			BigDecimal priceSingleItem = price.multiply(BigDecimal.valueOf(item.quantity()));
			totalPrice = totalPrice.add(priceSingleItem);
		}

		return totalPrice;
	}
}
