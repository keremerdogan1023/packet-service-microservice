package com.kerem.packetservicee.service.dto.requests.create;


import com.kerem.commonpackage.utils.annotations.BlacklistName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAudiobookRequest {
    @BlacklistName()
    private String name;
    private int time;
    private String author;
    private String voiceOverOwner;
    private String provider;
    private String file;
}
