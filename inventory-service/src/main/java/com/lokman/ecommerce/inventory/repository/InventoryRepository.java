package com.lokman.ecommerce.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lokman.ecommerce.inventory.model.Inventory;

import jakarta.persistence.LockModeType;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("Select i From Inventory i where i.skuCode IN :skuCodes")
	List<Inventory> findBySkuCodeForUpdate(@Param("skuCodes") List<String> skuCodes);
}
