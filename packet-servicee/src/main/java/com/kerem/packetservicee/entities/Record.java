package com.kerem.packetservicee.entities;

import com.kerem.commonpackage.utils.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int packetId;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String filePath;
}