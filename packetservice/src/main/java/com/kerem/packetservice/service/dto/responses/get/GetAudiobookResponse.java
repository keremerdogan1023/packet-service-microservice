package com.kerem.packetservice.service.dto.responses.get;


import com.kerem.packetservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAudiobookResponse {
    private int id;
    private String name;
    private int time;
    private State state;
    private String author;
    private String voiceOverOwner;
    private String provider;
    private String filePath;
}


