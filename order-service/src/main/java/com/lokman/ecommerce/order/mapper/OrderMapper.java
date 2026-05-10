package com.lokman.ecommerce.order.mapper;

import java.math.BigDecimal;
import java.util.Map;

import com.lokman.ecommerce.order.model.Order;
import com.lokman.ecommerce.order.model.OrderItem;
import com.lokman.ecommerce.order.request.CreateOrderRequest;
import com.lokman.ecommerce.order.request.OrderItemRequest;

public class OrderMapper {

	public static Order toOrder(CreateOrderRequest request, Map<Long, BigDecimal> priceMap) {

		Order order = new Order();
		order.setUserId(request.userId());
		request.items().forEach(item -> order.addItem(toOrderItem(item, priceMap)));

		return order;
	}

	private static OrderItem toOrderItem(OrderItemRequest item, Map<Long, BigDecimal> priceMap) {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductId(item.productId());
		orderItem.setQuantity(item.quantity());
		orderItem.setPrice(priceMap.get(item.productId()));

		return orderItem;
	}
}
