package com.kerem.inventoryservice.service.kafka.consumer.SaleConsumers;

import com.kerem.commonpackage.events.sale.SaleCreatedEvent;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.service.abstracts.AudiobookInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumer {
    private final AudiobookInventoryService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "audiobook-order-created",
            groupId = "audiobook-order-create"
    )
    public void consume(SaleCreatedEvent event){
        var audiobook = mapper.forRequest().map(event, UpdateAudiobookInventoryRequest.class);
        service.updateForConsume(event.getMediaId(),audiobook);
        log.info("Audiobook order created events consumed {}",event);
    }
}
