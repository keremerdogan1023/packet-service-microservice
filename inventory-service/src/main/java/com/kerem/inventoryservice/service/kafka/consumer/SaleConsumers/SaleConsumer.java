package com.kerem.inventoryservice.service.kafka.consumer.SaleConsumers;

import com.kerem.commonpackage.events.sale.SaleCreatedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.service.abstracts.AudiobookInventoryService;
import com.kerem.inventoryservice.service.abstracts.PodcastInventoryService;
import com.kerem.inventoryservice.service.abstracts.RecordInventoryService;
import com.kerem.inventoryservice.service.abstracts.SongInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateSongInventoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumer {
    private final AudiobookInventoryService audiobookInventoryService;
    private final ModelMapperService mapper;
    private final PodcastInventoryService podcastInventoryService;
    private final RecordInventoryService recordInventoryService;
    private final SongInventoryService songInventoryService;
    @KafkaListener(
            topics = "audiobook-order-created",
            groupId = "audiobook-order-create"
    )
    public void consumeAudiobook(SaleCreatedEvent event){
        var audiobook = mapper.forRequest().map(event, UpdateAudiobookInventoryRequest.class);
        audiobookInventoryService.updateForConsume(event.getMediaId(),audiobook);
        log.info("Audiobook order created events consumed {}",event);
    }
    @KafkaListener(
            topics = "podcast-order-created",
            groupId = "podcast-order-create"
    )
    public void consumePodcast(SaleCreatedEvent event){
        var podcast = mapper.forRequest().map(event, UpdatePodcastInventoryRequest.class);
        podcastInventoryService.updateForConsume(event.getMediaId(),podcast);
        log.info("Podcast order created events consumed {}",event);
    }
    @KafkaListener(
            topics = "record-order-created",
            groupId = "record-order-create"
    )
    public void consumeRecord(SaleCreatedEvent event){
        var record = mapper.forRequest().map(event, UpdateRecordInventoryRequest.class);
        recordInventoryService.updateForConsume(event.getMediaId(),record);
        log.info("Record order created events consumed {}",event);
    }
    @KafkaListener(
            topics = "song-order-created",
            groupId = "song-order-create"
    )
    public void consumeSong(SaleCreatedEvent event){
        var song = mapper.forRequest().map(event, UpdateSongInventoryRequest.class);
        songInventoryService.updateForConsume(event.getMediaId(),song);
        log.info("Song order created events consumed {}",event);
    }
}
