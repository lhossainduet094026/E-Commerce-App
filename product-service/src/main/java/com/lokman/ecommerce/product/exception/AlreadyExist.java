package com.lokman.ecommerce.product.exception;

public class AlreadyExist extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyExist(String message) {
		super(message);
	}
}
