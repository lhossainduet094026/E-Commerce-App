package com.lokman.ecommerce.order.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.lokman.ecommerce.order.response.ProductResponse;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {

	@GetMapping
	List<ProductResponse> getProducts(@RequestHeader(name = "X-Correlation-Id") String correlationId , @RequestParam(name = "productIds") List<Long> ids);
}
