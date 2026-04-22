package com.lokman.ecommerce.product.orchestrator.dto.mapper;

import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.request.ProductWorkflowRequest;

public class ProductMapper {

	public static ProductCreateRequest toProductCreateRequest(ProductWorkflowRequest productWorkflowRequest) {
		
		return new ProductCreateRequest(productWorkflowRequest.skuCode(),
				productWorkflowRequest.name(), 
				productWorkflowRequest.description(),
				productWorkflowRequest.price());
	}
}
