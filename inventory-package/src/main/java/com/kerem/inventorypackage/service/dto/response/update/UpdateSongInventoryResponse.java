package com.kerem.inventorypackage.service.dto.response.update;

import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSongInventoryResponse {
    private int id;
    private int PacketId;
    private String name;
    private int time;
    private State state;
    private String author;
    private String voiceOverOwner;
    private String provider;
    private double price;
    private int quantity;
}
