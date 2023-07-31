package com.kerem.packetservice.entities;

import com.kerem.packetservice.entities.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Audiobooks")
public class Audiobook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int PacketId;
    private String name;
    private int time;
    private State state;
    private String author;
    private String voiceOverOwner;
    private String provider;
    private String filePath;
}

