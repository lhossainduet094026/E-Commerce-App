package com.lokman.ecommerce.order.response;

import java.math.BigDecimal;

public record ProductResponse(Long productId, String skuCode, BigDecimal price) {

}
