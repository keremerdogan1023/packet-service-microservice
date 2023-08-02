package com.kerem.inventorypackage.service.kafka.consumer;

import com.kerem.commonpackage.events.packet.create.RecordCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.events.packet.delete.RecordDeletedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventorypackage.entities.RecordInventory;
import com.kerem.inventorypackage.service.abstracts.RecordInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordPacketConsumer {
    private final RecordInventoryService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "record-created",
            groupId = "record-create"
    )
    public void consume(RecordCreatedEvent event){
        var record = mapper.forRequest().map(event, RecordInventory.class);
        service.add(record);
        log.info("Record created events consumed {}",event);
    }
    @KafkaListener(
            topics = "record-deleted",
            groupId = "record-delete"
    )
    public void consume(RecordDeletedEvent event){
        service.delete(event.getId());
        log.info("Record deleted events consumed{}",event);
    }
}
