package com.kerem.inventoryservice.service.kafka.consumer.PacketConsumers;

import com.kerem.commonpackage.events.packet.create.SongCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.SongDeletedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.SongInventory;
import com.kerem.inventoryservice.service.abstracts.SongInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongPacketConsumer {
    private final SongInventoryService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "song-created",
            groupId = "song-create"
    )
    public void consume(SongCreatedEvent event){
        var song = mapper.forRequest().map(event, SongInventory.class);
        service.add(song);
        log.info("Song created events consumed {}",event);
    }
    @KafkaListener(
            topics = "song-deleted",
            groupId = "song-delete"
    )
    public void consume(SongDeletedEvent event){
        service.delete(event.getId());
        log.info("Song deleted events consumed{}",event);
    }
}
