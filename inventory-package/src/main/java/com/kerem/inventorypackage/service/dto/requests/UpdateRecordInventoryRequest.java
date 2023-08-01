package com.kerem.inventorypackage.service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecordInventoryRequest {
    private double price;
    private int quantity;
}
