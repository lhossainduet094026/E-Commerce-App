package com.lokman.ecommerce.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokman.ecommerce.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
