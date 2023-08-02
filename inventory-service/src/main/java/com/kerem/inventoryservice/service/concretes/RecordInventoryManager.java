package com.kerem.inventoryservice.service.concretes;

import com.kerem.inventoryservice.entities.RecordInventory;
import com.kerem.inventoryservice.repository.RecordInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.RecordInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordInventoryManager implements RecordInventoryService {
    private final RecordInventoryRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllRecordInventoryResponse> getAll() {
        List<RecordInventory> recordInventories = repository.findAll();
        var response = recordInventories.stream()
                .map(recordInventory ->  mapper.map(recordInventories, GetAllRecordInventoryResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRecordInventoryResponse getById(int id) {
        RecordInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.map(inventory, GetRecordInventoryResponse.class);
        return response;
    }

    @Override
    public UpdateRecordInventoryResponse update(int id, UpdateRecordInventoryRequest request) {
        RecordInventory inventory = mapper.map(request,RecordInventory.class);
        inventory.setId(id);
        var response = mapper.map(inventory, UpdateRecordInventoryResponse.class);
        return response;
    }

    @Override
    public void add(RecordInventory inventory) {
        repository.save(inventory);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}