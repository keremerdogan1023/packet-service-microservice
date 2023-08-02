package com.kerem.salepackage.service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSaleRequest {
    private int id;
    private int packageId;
    private int mediaId;
    private int quantity;
}
