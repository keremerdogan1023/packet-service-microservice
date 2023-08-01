package com.kerem.inventorypackage.entities;

import com.kerem.commonpackage.utils.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "podcast-inventory")
public class PodcastInventory {
    @Id
    private int id;
    private int packetId;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String provider;
    private double price;
    private int quantity;

}
