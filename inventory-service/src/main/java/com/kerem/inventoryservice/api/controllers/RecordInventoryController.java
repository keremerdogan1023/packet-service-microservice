package com.kerem.inventoryservice.api.controllers;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.service.abstracts.PodcastInventoryService;
import com.kerem.inventoryservice.service.abstracts.RecordInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/record-inventory")
public class RecordInventoryController {
    private final RecordInventoryService service;
    @GetMapping
    public List<GetAllRecordInventoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRecordInventoryResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateRecordInventoryResponse update(@PathVariable int id, @Valid @RequestBody UpdateRecordInventoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @GetMapping("/get-record-for-sale/{mediaId}")
    public SaleClientResponse getRecordForSale(@PathVariable int mediaId){
        return service.getRecordForSale(mediaId);
    }
}
