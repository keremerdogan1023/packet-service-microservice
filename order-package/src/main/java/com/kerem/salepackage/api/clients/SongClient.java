package com.kerem.salepackage.api.clients;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "song-inventory-package", url = "http://localhost:8081")
public interface SongClient {
    @GetMapping(value = "/api/song-inventory/get-song-for-sale/{mediaId}")
    SaleClientResponse getSong(@PathVariable int mediaId);
}
