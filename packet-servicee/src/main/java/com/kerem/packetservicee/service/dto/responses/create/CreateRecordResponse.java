package com.kerem.packetservicee.service.dto.responses.create;

import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecordResponse {
    private int id;
    private String name;
    private int time;
    private State state;
    private String owner;
    private String filePath;
}
