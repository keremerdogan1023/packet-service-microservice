package com.kerem.inventoryservice.service.concretes;

import com.kerem.inventoryservice.entities.SongInventory;
import com.kerem.inventoryservice.repository.SongInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.SongInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateSongInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongInventoryManager implements SongInventoryService {
    private final SongInventoryRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllSongsInventoryResponse> getAll() {
        List<SongInventory> songInventories = repository.findAll();
        var response = songInventories.stream()
                .map(songInventory  ->  mapper.map(songInventories, GetAllSongsInventoryResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetSongInventoryResponse getById(int id) {
        SongInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.map(inventory, GetSongInventoryResponse.class);
        return response;
    }

    @Override
    public UpdateSongInventoryResponse update(int id, UpdateSongInventoryRequest request) {
        SongInventory inventory = mapper.map(request,SongInventory.class);
        inventory.setId(id);
        var response = mapper.map(inventory, UpdateSongInventoryResponse.class);
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
}
