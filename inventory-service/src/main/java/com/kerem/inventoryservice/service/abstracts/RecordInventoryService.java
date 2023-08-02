package com.kerem.inventoryservice.service.abstracts;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.entities.RecordInventory;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;

import java.util.List;

public interface RecordInventoryService {
    List<GetAllRecordInventoryResponse> getAll();
    GetRecordInventoryResponse getById(int id);
    UpdateRecordInventoryResponse update(int id, UpdateRecordInventoryRequest request);
    void add(RecordInventory inventory);
    void delete(int id);
    SaleClientResponse getRecordForSale(int mediaId);
    UpdateRecordInventoryResponse updateForConsume(int id, UpdateRecordInventoryRequest request);
}
