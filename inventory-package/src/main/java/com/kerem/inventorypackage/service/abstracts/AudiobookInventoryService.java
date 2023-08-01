package com.kerem.inventorypackage.service.abstracts;

import com.kerem.inventorypackage.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateAudiobookInventoryResponse;

import java.util.List;

public interface AudiobookInventoryService {
    List<GetAllAudiobooksInventoryResponse> getAll();
    GetAudiobookInventoryResponse getById(int id);
    UpdateAudiobookInventoryResponse update(int id, UpdateAudiobookInventoryRequest request);
}
