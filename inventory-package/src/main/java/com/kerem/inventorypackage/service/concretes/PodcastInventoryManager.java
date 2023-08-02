package com.kerem.inventorypackage.service.concretes;

import com.kerem.inventorypackage.entities.AudiobookInventory;
import com.kerem.inventorypackage.entities.PodcastInventory;
import com.kerem.inventorypackage.repository.AudiobookInventoryRepository;
import com.kerem.inventorypackage.repository.PodcastInventoryRepository;
import com.kerem.inventorypackage.service.abstracts.PodcastInventoryService;
import com.kerem.inventorypackage.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventorypackage.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdatePodcastInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PodcastInventoryManager implements PodcastInventoryService {
    private final PodcastInventoryRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllPodcastsInventoryResponse> getAll() {
        List<PodcastInventory> podcastInventories = repository.findAll();
        var response = podcastInventories.stream()
                .map(podcastInventory -> mapper.map(podcastInventories, GetAllPodcastsInventoryResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetPodcastInventoryResponse getById(int id) {
        PodcastInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.map(inventory, GetPodcastInventoryResponse.class);
        return response;
    }

    @Override
    public UpdatePodcastInventoryResponse update(int id, UpdatePodcastInventoryRequest request) {
        PodcastInventory inventory = mapper.map(request,PodcastInventory.class);
        inventory.setId(id);
        var response = mapper.map(inventory, UpdatePodcastInventoryResponse.class);
        return response;
    }

    @Override
    public void add(PodcastInventory inventory) {
        repository.save(inventory);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
