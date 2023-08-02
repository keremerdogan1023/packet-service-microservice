package com.kerem.inventoryservice.service.concretes;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.AudiobookInventory;
import com.kerem.inventoryservice.repository.AudiobookInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.AudiobookInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateAudiobookInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllAudiobooksInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateAudiobookInventoryResponse;
import lombok.AllArgsConstructor;
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
        List<GetAllAudiobooksInventoryResponse> response = audiobookInventories.stream()
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
        AudiobookInventory previousAudiobook = repository.findById(id).orElseThrow();
        AudiobookInventory inventory = mapper.forRequest().map(request,AudiobookInventory.class);
        var updatedAudiobook =keepUpdate(previousAudiobook,inventory);
        repository.save(updatedAudiobook);
        var response = mapper.forResponse().map(updatedAudiobook, UpdateAudiobookInventoryResponse.class);
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
    public void update(AudiobookInventory inventory){
        repository.save(inventory);
    }

    @Override
    public SaleClientResponse getAudiobookForSale(int id) {
        var audiobook = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(audiobook, SaleClientResponse.class);

        return response;
    }
    public AudiobookInventory keepUpdate(AudiobookInventory previousAudiobook, AudiobookInventory inventory){
        inventory.setId(previousAudiobook.getId());
        inventory.setName(previousAudiobook.getName());
        inventory.setAuthor(previousAudiobook.getAuthor());
        inventory.setProvider(previousAudiobook.getProvider());
        inventory.setState(previousAudiobook.getState());
        inventory.setPacketId(previousAudiobook.getPacketId());
        inventory.setTime(previousAudiobook.getTime());
        inventory.setVoiceOverOwner(previousAudiobook.getVoiceOverOwner());
        return inventory;
    }
    public UpdateAudiobookInventoryResponse updateForConsume(int id, UpdateAudiobookInventoryRequest request){
        AudiobookInventory previousAudiobook = repository.findById(id).orElseThrow();
        AudiobookInventory inventory = mapper.forRequest().map(request,AudiobookInventory.class);
        var updatedAudiobook =keepUpdate(previousAudiobook,inventory);
        updatedAudiobook.setPrice(previousAudiobook.getPrice());

        repository.save(updatedAudiobook);
        var response = mapper.forResponse().map(updatedAudiobook, UpdateAudiobookInventoryResponse.class);
        return response;
    }

}
