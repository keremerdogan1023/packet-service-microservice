package com.kerem.packetservice.service.dto.requests.create;

import com.kerem.packetservice.common.utils.annotations.BlacklistName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePodcastRequest {
    @BlacklistName()
    private String name;
    private int time;
    private String owner;
    private String provider;
    private String file;
}
