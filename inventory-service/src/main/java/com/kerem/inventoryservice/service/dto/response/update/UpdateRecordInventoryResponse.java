package com.kerem.inventoryservice.service.dto.response.update;

import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecordInventoryResponse {
    private int id;
    private int packetId;
    private String name;
    private int time;
    private State state;
    private String owner;
    private double price;
    private int quantity;
}
