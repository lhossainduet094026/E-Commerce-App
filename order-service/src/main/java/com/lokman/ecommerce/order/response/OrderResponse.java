package com.lokman.ecommerce.order.response;

public record OrderResponse( 
		Long orderId,
        String status,
        String message) {

}
