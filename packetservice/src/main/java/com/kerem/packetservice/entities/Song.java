package com.kerem.packetservice.entities;

import com.kerem.packetservice.entities.enums.State;
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
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int PacketId;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String provider;
    private String filePath;
}

