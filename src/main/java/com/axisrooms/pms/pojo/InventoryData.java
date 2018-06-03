package com.axisrooms.pms.pojo;

import lombok.Data;

@Data
public class InventoryData {

    private String accessKey;
    private String channelId;
    private String hotelId;
    private String roomId;

    public InventoryData(String accessKey, String channelId, String hotelId, String roomId) {
        this.accessKey = accessKey;
        this.channelId = channelId;
        this.hotelId   = hotelId;
        this.roomId    = roomId;
    }

}
