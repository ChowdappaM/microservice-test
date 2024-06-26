package com.miroservice.inventory.controller;

import com.miroservice.inventory.dto.InventoryResponse;
import com.miroservice.inventory.model.Inventory;
import com.miroservice.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    @GetMapping("{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryService.getInventoryDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createInventory(@RequestBody Inventory inventory) {
        inventoryService.createInventory(inventory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/code")
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStockIn(skuCode);
    }
    private InventoryService inventoryService;
    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
}
