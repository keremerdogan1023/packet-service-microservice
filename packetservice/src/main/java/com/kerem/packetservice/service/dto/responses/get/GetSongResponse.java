package com.kerem.packetservice.service.dto.responses.get;

import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetSongResponse {
    private int id;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String provider;
    private String filePath;
}
