package com.miroservice.inventory.dao;

import com.miroservice.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<List<Inventory>> findBySkuCodeIn(List<String> skuCodes);
}
