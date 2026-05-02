package com.lokman.ecommerce.product.query.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.query.exception.ProductNotfoundException;
import com.lokman.ecommerce.product.query.external.client.InventoryClient;
import com.lokman.ecommerce.product.query.external.client.ProductClient;
import com.lokman.ecommerce.product.query.response.InventoryResponse;
import com.lokman.ecommerce.product.query.response.ProductResponse;
import com.lokman.ecommerce.product.query.response.ProductWithInventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueryService {

	private final ProductClient productClient;
	private final InventoryClient inventoryClient;

	public List<ProductWithInventoryResponse> getProductList(int page, int limit) {

		List<ProductResponse> products = productClient.getProducts(page, limit);

		if (products == null || products.isEmpty()) {
		    throw new ProductNotfoundException("Product not found");
		}

		List<String> skuCodes = products.stream().map(ProductResponse::skuCode).toList();

		List<InventoryResponse> inventories = inventoryClient.getInventories(skuCodes);
		
		List<InventoryResponse> safeInventories =  Optional.ofNullable(inventories).orElse(List.of());

		return mergeProductWithInventories(products, safeInventories);

	}

	private List<ProductWithInventoryResponse> mergeProductWithInventories(List<ProductResponse> products, List<InventoryResponse> inventories) {

		Map<String, Integer> quantityMap = inventories.stream()
				.collect(Collectors.toMap(InventoryResponse::skuCode,
				InventoryResponse::quantity, 
				(oldValue, newValue) -> newValue
				));
		
		List<ProductWithInventoryResponse> response = products.stream().map(product -> new ProductWithInventoryResponse(product.id(), 
				product.name(),
				product.description(), 
				product.skuCode(), 
				quantityMap.getOrDefault(product.skuCode(), 0))).toList();
		
		return response;

	}
}
