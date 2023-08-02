package com.kerem.inventoryservice.service.kafka.consumer.PacketConsumers;

import com.kerem.commonpackage.events.packet.create.PodcastCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.PodcastDeletedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.PodcastInventory;
import com.kerem.inventoryservice.service.abstracts.PodcastInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PodcastPacketConsumer {
    private final PodcastInventoryService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "podcast-created",
            groupId = "podcast-create"
    )
    public void consume(PodcastCreatedEvent event){
        var podcast = mapper.forRequest().map(event, PodcastInventory.class);
        service.add(podcast);
        log.info("Audiobook created events consumed {}",event);
    }
    @KafkaListener(
            topics = "podcast-deleted",
            groupId = "podcast-delete"
    )
    public void consume(PodcastDeletedEvent event){
        service.delete(event.getId());
        log.info("Podcast deleted events consumed{}",event);
    }
}

