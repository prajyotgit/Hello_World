package com.axisrooms.pms.controller;

import com.axisrooms.pms.pojo.InventoryData;
import com.axisrooms.pms.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@RestController
@Controller
@Slf4j
public class PMSInventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public InventoryData loadInventory(@RequestHeader Map<String, String> param) {
        param.entrySet().stream().forEach(System.out::println);
        return inventoryService.getData(param);
    }

}

