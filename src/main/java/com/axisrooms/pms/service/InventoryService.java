package com.axisrooms.pms.service;

import com.axisrooms.pms.pojo.InventoryData;
import com.axisrooms.pms.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public InventoryData getData(Map<String, String> param) {
        InventoryData data = new InventoryData(
        param.get("accessKey"),
        param.get("channelId"),
        param.get("hotelId"),
        param.get("roomId"));

        return data;
    }
}
