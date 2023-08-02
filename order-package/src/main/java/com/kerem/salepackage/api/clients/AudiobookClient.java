package com.kerem.salepackage.api.clients;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "inventory-package", url = "http://localhost:8081")
public interface AudiobookClient {
    @GetMapping(value = "/api/audiobook-inventory/get-audiobook-for-sale/{mediaId}")
    SaleClientResponse getAudiobook(@PathVariable int mediaId);
}
