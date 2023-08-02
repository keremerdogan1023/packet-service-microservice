package com.kerem.salepackage.service.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSalesResponse {
    private int id;
    private int packageId;
    private int mediaId;
    private double totalPrice;
    private int quantity;
    private LocalDate saledAt;

}
