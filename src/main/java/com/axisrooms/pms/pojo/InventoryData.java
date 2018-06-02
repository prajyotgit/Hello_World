package com.axisrooms.pms.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class InventoryData {

    @JsonProperty("accessKey")
    String accessKey;

    @JsonProperty("channelId")
    String channelId;

    @JsonProperty("hotelId")
    String hotelId;

    @JsonProperty("roomId")
    String roomId;

    public InventoryData(String accessKey, String channelId, String hotelId, String roomId) {
        this.accessKey = accessKey;
        this.channelId = channelId;
        this.hotelId = hotelId;
        this.roomId = roomId;
    }

}
