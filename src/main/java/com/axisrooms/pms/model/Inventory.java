package com.axisrooms.pms.model;

import javax.persistence.*;

@Entity
@Table(name = "pms_inventory", catalog = "pms", uniqueConstraints = {@UniqueConstraint(name = "ID", columnNames = {"id"})})
public class Inventory {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "access_key")
    String accessKey;

    @Column(name = "channel_id")
    String channelId;

    @Column(name = "hotel_id")
    String hotelId;

    @Column(name = "room_id")
    String roomId;

}
