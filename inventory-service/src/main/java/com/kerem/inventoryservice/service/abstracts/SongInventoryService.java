package com.kerem.inventoryservice.service.abstracts;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.entities.SongInventory;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateSongInventoryResponse;

import java.util.List;

public interface SongInventoryService {
    List<GetAllSongsInventoryResponse> getAll();
    GetSongInventoryResponse getById(int id);
    UpdateSongInventoryResponse update(int id, UpdateSongInventoryRequest request);
    void add(SongInventory inventory);
    void delete(int id);
    SaleClientResponse getSongForSale(int mediaId);
    UpdateSongInventoryResponse updateForConsume(int id, UpdateSongInventoryRequest request);
}
