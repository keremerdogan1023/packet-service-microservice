package com.kerem.inventoryservice.service.abstracts;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.inventoryservice.entities.PodcastInventory;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;

import java.util.List;

public interface PodcastInventoryService {
    List<GetAllPodcastsInventoryResponse> getAll();
    GetPodcastInventoryResponse getById(int id);
    UpdatePodcastInventoryResponse update(int id, UpdatePodcastInventoryRequest request);
    void add(PodcastInventory inventory);
    void delete(int id);
    SaleClientResponse getPodcastForSale(int mediaId);
    UpdatePodcastInventoryResponse updateForConsume(int id, UpdatePodcastInventoryRequest request);
}
