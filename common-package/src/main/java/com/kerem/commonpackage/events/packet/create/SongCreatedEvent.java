package com.kerem.commonpackage.events.packet.create;

import com.kerem.commonpackage.events.Event;
import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongCreatedEvent implements Event {
    private int id;
    private int packetId;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String provider;
}
