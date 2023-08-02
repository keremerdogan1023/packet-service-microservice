package com.kerem.salepackage.api.clients;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "record-inventory-package", url = "http://localhost:8081")
public interface RecordClient {
    @GetMapping(value = "/api/record-inventory/get-record-for-sale/{mediaId}")
    SaleClientResponse getRecord(@PathVariable int mediaId);
}