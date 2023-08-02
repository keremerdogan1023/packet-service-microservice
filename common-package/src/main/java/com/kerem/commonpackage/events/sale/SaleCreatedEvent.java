package com.kerem.commonpackage.events.sale;

import com.kerem.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleCreatedEvent implements Event {

    private int packageId;
    private int mediaId;
    private double price;
    private int quantity;
    private LocalDate saledAt;

}
