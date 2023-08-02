package com.kerem.inventoryservice.api.controllers;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.service.abstracts.AudiobookInventoryService;
import com.kerem.inventoryservice.service.abstracts.PodcastInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/podcast-inventory")
public class PodcastInventoryController {
    private final PodcastInventoryService service;
    @GetMapping
    public List<GetAllPodcastsInventoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPodcastInventoryResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdatePodcastInventoryResponse update(@PathVariable int id, @Valid @RequestBody UpdatePodcastInventoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @GetMapping("/get-podcast-for-sale/{mediaId}")
    public SaleClientResponse getPodcastForSale(@PathVariable int mediaId){
        return service.getPodcastForSale(mediaId);
    }
}