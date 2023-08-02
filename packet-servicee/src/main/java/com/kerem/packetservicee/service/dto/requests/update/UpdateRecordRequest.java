package com.kerem.packetservicee.service.dto.requests.update;

import com.kerem.commonpackage.utils.annotations.BlacklistName;
import com.kerem.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecordRequest {
    @BlacklistName()
    private String name;
    private int time;
    private State state;
    private String owner;
}
