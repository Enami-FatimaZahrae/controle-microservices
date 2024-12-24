package com.controle.inventoryservice.controller;

import com.controle.inventoryservice.dto.InventoryResponse;
import com.controle.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @QueryMapping
    public List<InventoryResponse> isInStock(@Argument List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }
}
