package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.mapper.OrchestratorMapper;
import com.lokman.ecommerce.product.orchestrator.dto.mapper.ProductMapper;
import com.lokman.ecommerce.product.orchestrator.dto.request.InventoryCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.request.ProductWorkflowRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.InventoryUpdateResponse;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductResponse;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductWorkflowResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductWorkflowService {

	private final ProductService productService;

	private final InventoryService inventoryService;

	public ProductWorkflowResponse createProductWorkflow(ProductWorkflowRequest productWorkflowRequest) {

		ProductCreateRequest productCreateRequest = ProductMapper.toProductCreateRequest(productWorkflowRequest);

		ProductResponse productResponse = productService.createProduct(productCreateRequest);

		InventoryUpdateResponse inventoryResponse = inventoryService.upsertInventory(
				new InventoryCreateRequest(productCreateRequest.skuCode(), productWorkflowRequest.quantity()));

		if (inventoryResponse.status().equals("FAILED")) {
			productService.deleteProduct(productResponse.id()); // compensation
			throw new RuntimeException("Inventory failed, product rolled back");
		}

		ProductWorkflowResponse productWorkflowResponse = OrchestratorMapper.toProductWorkflowResponse(productResponse,
				inventoryResponse);

		return productWorkflowResponse;
	}
}
