package com.kerem.inventoryservice.service.concretes;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.PodcastInventory;
import com.kerem.inventoryservice.entities.RecordInventory;
import com.kerem.inventoryservice.entities.SongInventory;
import com.kerem.inventoryservice.repository.SongInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.SongInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAudiobookInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateSongInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongInventoryManager implements SongInventoryService {
    private final SongInventoryRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllSongsInventoryResponse> getAll() {
        List<SongInventory> songs = repository.findAll();
        List<GetAllSongsInventoryResponse> response = songs.stream()
                .map(songInventory -> mapper.forResponse().map(songInventory, GetAllSongsInventoryResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetSongInventoryResponse getById(int id) {
        SongInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(inventory, GetSongInventoryResponse.class);
        return response;
    }

    @Override
    public UpdateSongInventoryResponse update(int id, UpdateSongInventoryRequest request) {
        SongInventory previous = repository.findById(id).orElseThrow();
        SongInventory inventory = mapper.forRequest().map(request,SongInventory.class);
        var updated =keepUpdate(previous,inventory);
        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdateSongInventoryResponse.class);
        return response;
    }

    @Override
    public void add(SongInventory inventory) {
        repository.save(inventory);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public SaleClientResponse getSongForSale(int id) {
        var song = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(song, SaleClientResponse.class);

        return response;
    }
    public SongInventory keepUpdate(SongInventory previous, SongInventory inventory){
        inventory.setId(previous.getId());
        inventory.setName(previous.getName());
        inventory.setState(previous.getState());
        inventory.setPacketId(previous.getPacketId());
        inventory.setTime(previous.getTime());
        inventory.setOwner(previous.getOwner());
        inventory.setProvider(previous.getProvider());
        return inventory;
    }

    @Override
    public UpdateSongInventoryResponse updateForConsume(int id, UpdateSongInventoryRequest request) {
        SongInventory previous = repository.findById(id).orElseThrow();
        SongInventory inventory = mapper.forRequest().map(request,SongInventory.class);
        var updated =keepUpdate(previous,inventory);
        updated.setPrice(previous.getPrice());

        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdateSongInventoryResponse.class);
        return response;
    }
}
