package com.spring.inventoryservice.controller;

import com.spring.inventoryservice.model.Inventory;
import com.spring.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    private final InventoryRepository inventoryRepository;

    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode){
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannnot find product " +
                        "by sku code" + skuCode));
        return inventory.getStock() > 0;
    }
}
