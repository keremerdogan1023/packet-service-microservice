package com.kerem.commonpackage.events.packet.delete;

import com.kerem.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDeletedEvent implements Event {
    private int id;
}
