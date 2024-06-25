package com.miroservice.inventory;

import com.miroservice.inventory.dao.InventoryRepository;
import com.miroservice.inventory.model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventorySeviceApp {
    public static void main(String[] args) {
        SpringApplication.run(InventorySeviceApp.class, args);

    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setQuantity(100);
            inventory.setSkuCode("IPhone_13");
            inventoryRepository.save(inventory);

            Inventory inventory2 = new Inventory();
            inventory2.setQuantity(0);
            inventory2.setSkuCode("IPhone_12");
            inventoryRepository.save(inventory2);
        };
    }
}