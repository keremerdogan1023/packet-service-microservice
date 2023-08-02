package com.kerem.salepackage.api.clients;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "podcast-inventory-package", url = "http://localhost:8081")
public interface PodcastClient {
    @GetMapping(value = "/api/podcast-inventory/get-podcast-for-sale/{mediaId}")
    SaleClientResponse getPodcast(@PathVariable int mediaId);
}
