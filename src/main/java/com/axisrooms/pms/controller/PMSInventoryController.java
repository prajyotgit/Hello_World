package com.axisrooms.pms.controller;

import com.axisrooms.pms.model.Inventory;
import com.axisrooms.pms.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class PMSInventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public Inventory loadInventory(@RequestHeader Map<String, String> param) {
        return inventoryService.saveData(param);
    }

}

