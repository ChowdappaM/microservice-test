package com.miroservice.inventory.service;

import com.miroservice.inventory.dao.InventoryRepository;
import com.miroservice.inventory.dto.InventoryResponse;
import com.miroservice.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<InventoryResponse> isInStockIn(List<String> skuCode) {
        Optional<List<Inventory>> result =  inventoryRepository.findBySkuCodeIn(skuCode);

        return
                result.get().stream().map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()).toList();

    }
    private InventoryRepository inventoryRepository;

}
