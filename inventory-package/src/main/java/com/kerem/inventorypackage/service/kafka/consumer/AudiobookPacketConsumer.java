package com.kerem.inventorypackage.service.kafka.consumer;

import com.kerem.commonpackage.events.packet.create.AudiobookCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventorypackage.entities.AudiobookInventory;
import com.kerem.inventorypackage.service.abstracts.AudiobookInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudiobookPacketConsumer {
    private final AudiobookInventoryService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "audiobook-created",
            groupId = "audiobook-create"
    )
    public void consume(AudiobookCreatedEvent event){
        var audiobook = mapper.forRequest().map(event, AudiobookInventory.class);
        service.add(audiobook);
        log.info("Audiobook created events consumed {}",event);
    }
    @KafkaListener(
            topics = "audiobook-deleted",
            groupId = "audiobook-delete"
    )
    public void consume(AudiobookDeletedEvent event){
        service.delete(event.getId());
        log.info("Audiobook deleted events consumed{}",event);
    }
}
