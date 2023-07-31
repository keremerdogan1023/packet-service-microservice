package com.kerem.packetservice.service.dto.requests.update;

import com.kerem.packetservice.common.utils.annotations.BlacklistName;
import com.kerem.packetservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAudiobookRequest {
    @BlacklistName()
    private String name;
    private int time;
    private State state;
    private String author;
    private String voiceOverOwner;
    private String provider;
}

