package com.kerem.commonpackage.events.packet.create;

import com.kerem.commonpackage.events.Event;
import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AudiobookCreatedEvent implements Event {
    private int id;
    private int packetId;
    private String name;
    private int time;
    private State state;
    private String author;
    private String voiceOverOwner;
    private String provider;
}
