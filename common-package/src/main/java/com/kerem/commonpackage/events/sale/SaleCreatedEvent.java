package com.kerem.commonpackage.events.sale;

import com.kerem.commonpackage.events.Event;

import java.time.LocalDate;

public class SaleCreatedEvent implements Event {

    private int packageId;
    private int mediaId;
    private double price;
    private int quantity;
    private LocalDate saledAt;

}
