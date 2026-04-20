package com.lokman.ecommerce.product.exception;

public class InventoryFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InventoryFailedException(String message) {
		super(message);
	}
}
