package com.lokman.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokman.ecommerce.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsBySkuCode(String skuCode);
}
