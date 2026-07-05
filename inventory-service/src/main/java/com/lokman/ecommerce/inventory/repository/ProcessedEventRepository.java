package com.lokman.ecommerce.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokman.ecommerce.inventory.model.ProcessedEvent;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, Long> {

}
