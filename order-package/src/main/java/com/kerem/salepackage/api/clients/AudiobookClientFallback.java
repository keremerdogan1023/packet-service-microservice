package com.kerem.salepackage.api.clients;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AudiobookClientFallback implements AudiobookClient {
    @Override
    public SaleClientResponse getAudiobook(int mediaId) {
        log.info("INVENTORY SERVICE IS DOWN");
        throw new RuntimeException("INVENTORY-SERVICE NOT AVAILABLE RIGHT NOW!!");
    }
}
