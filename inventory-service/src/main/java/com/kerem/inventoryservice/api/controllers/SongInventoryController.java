package com.kerem.inventoryservice.api.controllers;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.service.abstracts.RecordInventoryService;
import com.kerem.inventoryservice.service.abstracts.SongInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateSongInventoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/song-inventory")
public class SongInventoryController {
    private final SongInventoryService service;
    @GetMapping
    public List<GetAllSongsInventoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSongInventoryResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateSongInventoryResponse update(@PathVariable int id, @Valid @RequestBody UpdateSongInventoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @GetMapping("/get-song-for-sale/{mediaId}")
    public SaleClientResponse getSongForSale(@PathVariable int mediaId){
        return service.getSongForSale(mediaId);
    }
}
