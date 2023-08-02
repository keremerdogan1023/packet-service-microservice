package com.kerem.inventorypackage.service.concretes;

import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventorypackage.entities.AudiobookInventory;
import com.kerem.inventorypackage.repository.AudiobookInventoryRepository;
import com.kerem.inventorypackage.service.abstracts.AudiobookInventoryService;
import com.kerem.inventorypackage.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateAudiobookInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AudiobookInventoryManager implements AudiobookInventoryService {
    private final AudiobookInventoryRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllAudiobooksInventoryResponse> getAll() {
        List<AudiobookInventory> audiobookInventories = repository.findAll();
        var response = audiobookInventories.stream()
                .map(audiobookInventory -> mapper.forResponse().map(audiobookInventory, GetAllAudiobooksInventoryResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetAudiobookInventoryResponse getById(int id) {
        AudiobookInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(inventory, GetAudiobookInventoryResponse.class);
        return response;
    }

    @Override
    public UpdateAudiobookInventoryResponse update(int id, UpdateAudiobookInventoryRequest request) {
        AudiobookInventory inventory = mapper.forRequest().map(request,AudiobookInventory.class);
        inventory.setId(id);
        var response = mapper.forResponse().map(inventory, UpdateAudiobookInventoryResponse.class);
        return response;
    }

    @Override
    public void add(AudiobookInventory inventory) {
        repository.save(inventory);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
