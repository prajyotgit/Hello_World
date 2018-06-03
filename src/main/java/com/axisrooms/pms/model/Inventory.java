package com.axisrooms.pms.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pms_inventory", catalog = "pms", uniqueConstraints = {@UniqueConstraint(name = "ID", columnNames = {"id"})})
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
