package com.lokman.ecommerce.product.orchestrator.exception;

public class ProductCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductCreationException(String message) {
		super(message);
	}

	public ProductCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}
