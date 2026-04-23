package com.lokman.ecommerce.product.orchestrator.dto.mapper;

import com.lokman.ecommerce.product.orchestrator.dto.response.InventoryUpdateResponse;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductResponse;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductWorkflowResponse;

public class OrchestratorMapper {

	public static ProductWorkflowResponse toProductWorkflowResponse(ProductResponse productResponse, InventoryUpdateResponse inventoryResponse) {
		return new ProductWorkflowResponse(productResponse.id(), 
				productResponse.name(), 
				productResponse.description(),
				productResponse.skuCode(), 
				inventoryResponse.quantity());
	}
}
