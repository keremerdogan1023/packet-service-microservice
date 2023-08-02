package com.kerem.inventorypackage.service.concretes;

import com.kerem.inventorypackage.entities.RecordInventory;
import com.kerem.inventorypackage.entities.SongInventory;
import com.kerem.inventorypackage.repository.RecordInventoryRepository;
import com.kerem.inventorypackage.repository.SongInventoryRepository;
import com.kerem.inventorypackage.service.abstracts.SongInventoryService;
import com.kerem.inventorypackage.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventorypackage.service.dto.requests.UpdateSongInventoryRequest;
import com.kerem.inventorypackage.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetAllSongsInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.get.GetSongInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateRecordInventoryResponse;
import com.kerem.inventorypackage.service.dto.response.update.UpdateSongInventoryResponse;
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
