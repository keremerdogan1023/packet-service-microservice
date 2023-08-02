package com.kerem.inventoryservice.api.controllers;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.service.abstracts.AudiobookInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/audiobook-inventory")
public class AudiobookInventoryController {
    private final AudiobookInventoryService service;
    @GetMapping
    public List<GetAllAudiobooksInventoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAudiobookInventoryResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateAudiobookInventoryResponse update(@PathVariable int id, @Valid @RequestBody UpdateAudiobookInventoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @GetMapping("/get-audiobook-for-sale/{mediaId}")
    public SaleClientResponse getAudiobookForSale(@PathVariable int mediaId){
        return service.getAudiobookForSale(mediaId);
    }
}

