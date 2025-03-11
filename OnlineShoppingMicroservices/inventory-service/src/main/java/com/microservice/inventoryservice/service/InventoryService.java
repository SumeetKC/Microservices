package com.microservice.inventoryservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public boolean isInStock(String skuCode) {
		return inventoryRepository.findBySkuCode(skuCode).isPresent();
	}
}
