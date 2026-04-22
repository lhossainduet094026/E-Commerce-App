package com.lokman.ecommerce.product.orchestrator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokman.ecommerce.product.orchestrator.dto.mapper.ProductMapper;
import com.lokman.ecommerce.product.orchestrator.dto.request.ProductCreateRequest;
import com.lokman.ecommerce.product.orchestrator.dto.request.ProductWorkflowRequest;
import com.lokman.ecommerce.product.orchestrator.dto.response.ProductWorkflowResponse;
import com.lokman.ecommerce.product.orchestrator.service.ProductWorkflowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/product-workflow")
public class ProductWorkflowController {

	private final ProductWorkflowService productWorkflowService;
	
	@PostMapping
	public ProductWorkflowResponse createProuctWorkflow(@Valid @RequestBody ProductWorkflowRequest productWorkflowRequest) {
		
		//need to work
		ProductCreateRequest productCreateRequest = ProductMapper.toProductCreateRequest(productWorkflowRequest);
		
		ProductWorkflowResponse workflowResponse = productWorkflowService.createProductWorkflow(productCreateRequest);
		
		return workflowResponse;
	}
}
