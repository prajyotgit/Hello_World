package com.axisrooms.pms.service;

import com.axisrooms.pms.model.Inventory;
import com.axisrooms.pms.pojo.InventoryData;
import com.axisrooms.pms.repo.InventoryRepository;
import com.axisrooms.pms.utils.Xfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory saveData(Map<String, String> param) {
        InventoryData data = new InventoryData(
        param.get("accesskey"),
        param.get("channelid"),
        param.get("hotelid"),
        param.get("roomid"));

        return inventoryRepository.save(Xfer.copyProperties(data, new Inventory()));
    }
}
