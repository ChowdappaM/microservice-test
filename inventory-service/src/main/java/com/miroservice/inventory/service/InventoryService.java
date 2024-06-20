package com.miroservice.inventory.service;

import com.miroservice.inventory.dao.InventoryRepository;
import com.miroservice.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public Inventory getInventoryDetails(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public void createInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    private InventoryRepository inventoryRepository;

}
