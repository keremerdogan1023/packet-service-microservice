package com.kerem.inventoryservice.service.concretes;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.inventoryservice.entities.PodcastInventory;
import com.kerem.inventoryservice.entities.RecordInventory;
import com.kerem.inventoryservice.repository.RecordInventoryRepository;
import com.kerem.inventoryservice.service.abstracts.RecordInventoryService;
import com.kerem.inventoryservice.service.dto.requests.UpdateRecordInventoryRequest;
import com.kerem.inventoryservice.service.dto.response.get.GetAllPodcastsInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetAllRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.get.GetRecordInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdatePodcastInventoryResponse;
import com.kerem.inventoryservice.service.dto.response.update.UpdateRecordInventoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordInventoryManager implements RecordInventoryService {
    private final RecordInventoryRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllRecordInventoryResponse> getAll() {
        List<RecordInventory> records = repository.findAll();
        List<GetAllRecordInventoryResponse> response = records.stream()
                .map(recordInventory -> mapper.forResponse().map(recordInventory, GetAllRecordInventoryResponse.class))
                .toList();
        return response;
    }


    @Override
    public GetRecordInventoryResponse getById(int id) {
        RecordInventory inventory = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(inventory, GetRecordInventoryResponse.class);
        return response;
    }

    @Override
    public UpdateRecordInventoryResponse update(int id, UpdateRecordInventoryRequest request) {
        RecordInventory previous = repository.findById(id).orElseThrow();
        RecordInventory inventory = mapper.forRequest().map(request,RecordInventory.class);
        var updated =keepUpdate(previous,inventory);
        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdateRecordInventoryResponse.class);
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

    @Override
    public SaleClientResponse getRecordForSale(int id) {
        var record = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(record, SaleClientResponse.class);

        return response;
    }
    public RecordInventory keepUpdate(RecordInventory previous, RecordInventory inventory){
        inventory.setId(previous.getId());
        inventory.setName(previous.getName());
        inventory.setState(previous.getState());
        inventory.setPacketId(previous.getPacketId());
        inventory.setTime(previous.getTime());
        inventory.setOwner(previous.getOwner());
        return inventory;
    }

    @Override
    public UpdateRecordInventoryResponse updateForConsume(int id, UpdateRecordInventoryRequest request) {
        RecordInventory previous = repository.findById(id).orElseThrow();
        RecordInventory inventory = mapper.forRequest().map(request,RecordInventory.class);
        var updated =keepUpdate(previous,inventory);
        updated.setPrice(previous.getPrice());

        repository.save(updated);
        var response = mapper.forResponse().map(updated, UpdateRecordInventoryResponse.class);
        return response;
    }
}
