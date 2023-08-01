package com.kerem.inventorypackage.service.abstracts;

import com.kerem.inventorypackage.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventorypackage.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateRecordInventoryResponse;

import java.util.List;

public interface RecordInventoryService {
    List<GetAllRecordInventoryResponse> getAll();
    GetRecordInventoryResponse getById(int id);
    UpdateRecordInventoryResponse update(int id, UpdateRecordInventoryRequest request);
}
