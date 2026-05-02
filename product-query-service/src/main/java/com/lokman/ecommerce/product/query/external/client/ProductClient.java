package com.lokman.ecommerce.product.query.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lokman.ecommerce.product.query.response.ProductResponse;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {

	@GetMapping
	List<ProductResponse> getProducts(@RequestParam("page") int page, @RequestParam("limit") int limit);
}
