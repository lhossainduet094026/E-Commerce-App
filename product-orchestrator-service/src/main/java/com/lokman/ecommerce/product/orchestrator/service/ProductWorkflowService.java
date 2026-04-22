package com.lokman.ecommerce.product.orchestrator.service;

import org.springframework.stereotype.Service;

import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductWorkflowResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductWorkflowService {

	public ProductWorkflowResponse createProductWorkflow(ProductCreateRequest productCreateRequest) {

		ProductWorkflowResponse productWorkflowResponse = null;

		return productWorkflowResponse;
	}
}
