package com.kerem.inventorypackage.service.abstracts;

import com.kerem.inventorypackage.entities.AudiobookInventory;
import com.kerem.inventorypackage.entities.PodcastInventory;
import com.kerem.inventorypackage.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventorypackage.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdatePodcastInventoryResponse;

import java.util.List;

public interface PodcastInventoryService {
    List<GetAllPodcastsInventoryResponse> getAll();
    GetPodcastInventoryResponse getById(int id);
    UpdatePodcastInventoryResponse update(int id, UpdatePodcastInventoryRequest request);
    void add(PodcastInventory inventory);
    void delete(int id);
}
