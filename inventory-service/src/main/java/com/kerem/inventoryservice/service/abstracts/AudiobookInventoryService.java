package com.kerem.inventoryservice.service.abstracts;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.entities.AudiobookInventory;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;

import java.util.List;

public interface AudiobookInventoryService {
    List<GetAllAudiobooksInventoryResponse> getAll();
    GetAudiobookInventoryResponse getById(int id);
    UpdateAudiobookInventoryResponse update(int id, UpdateAudiobookInventoryRequest request);
    void add(AudiobookInventory inventory);
    void delete(int id);
    SaleClientResponse getAudiobookForSale(int mediaId);
    void update(AudiobookInventory inventory);
    UpdateAudiobookInventoryResponse updateForConsume(int id, UpdateAudiobookInventoryRequest request);
}
