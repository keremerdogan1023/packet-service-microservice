package com.kerem.inventorypackage.service.abstracts;

import com.kerem.inventorypackage.entities.AudiobookInventory;
import com.kerem.inventorypackage.entities.SongInventory;
import com.kerem.inventorypackage.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventorypackage.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateSongInventoryResponse;

import java.util.List;

public interface SongInventoryService {
    List<GetAllSongsInventoryResponse> getAll();
    GetSongInventoryResponse getById(int id);
    UpdateSongInventoryResponse update(int id, UpdateSongInventoryRequest request);
    void add(SongInventory inventory);
    void delete(int id);
}
