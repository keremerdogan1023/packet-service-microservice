package com.kerem.inventoryservice.service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePodcastInventoryRequest {
    private double price;
    private int quantity;
}
