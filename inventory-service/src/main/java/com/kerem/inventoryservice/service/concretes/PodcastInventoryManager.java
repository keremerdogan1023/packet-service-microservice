package com.kerem.inventoryservice.service.concretes;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.AudiobookInventory;
import com.kerem.inventoryservice.entities.PodcastInventory;
import com.kerem.inventoryservice.repository.PodcastInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.PodcastInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdatePodcastInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetPodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PodcastInventoryManager implements PodcastInventoryService {
    private final PodcastInventoryRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllPodcastsInventoryResponse> getAll() {
        List<PodcastInventory> podcasts = repository.findAll();
        List<GetAllPodcastsInventoryResponse> response = podcasts.stream()
                .map(podcast -> mapper.forResponse().map(podcast, GetAllPodcastsInventoryResponse.class))
                .toList();
        return response;
    }
    @Override
    public GetPodcastInventoryResponse getById(int id) {
        PodcastInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(inventory, GetPodcastInventoryResponse.class);
        return response;
    }

    @Override
    public UpdatePodcastInventoryResponse update(int id, UpdatePodcastInventoryRequest request) {
        PodcastInventory previous = repository.findById(id).orElseThrow();
        PodcastInventory inventory = mapper.forRequest().map(request,PodcastInventory.class);
        var updated =keepUpdate(previous,inventory);
        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdatePodcastInventoryResponse.class);
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

    @Override
    public SaleClientResponse getPodcastForSale(int id) {
        var podcast = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(podcast, SaleClientResponse.class);

        return response;
    }
    public PodcastInventory keepUpdate(PodcastInventory previous, PodcastInventory inventory){
        inventory.setId(previous.getId());
        inventory.setName(previous.getName());
        inventory.setProvider(previous.getProvider());
        inventory.setState(previous.getState());
        inventory.setPacketId(previous.getPacketId());
        inventory.setTime(previous.getTime());
        inventory.setOwner(previous.getOwner());
        return inventory;
    }

    @Override
    public UpdatePodcastInventoryResponse updateForConsume(int id, UpdatePodcastInventoryRequest request) {
        PodcastInventory previous = repository.findById(id).orElseThrow();
        PodcastInventory inventory = mapper.forRequest().map(request,PodcastInventory.class);
        var updated =keepUpdate(previous,inventory);
        updated.setPrice(previous.getPrice());

        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdatePodcastInventoryResponse.class);
        return response;
    }
}
