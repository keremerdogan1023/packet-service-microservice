package com.kerem.packetservice.api.controllers;

import com.kerem.packetservice.service.abstracts.RecordService;
import com.kerem.packetservice.service.dto.requests.create.CreateRecordRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateRecordRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateRecordResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllRecordsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetRecordResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateRecordResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService service;
    @GetMapping
    public List<GetAllRecordsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRecordResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRecordResponse add(@Valid  @RequestBody CreateRecordRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateRecordResponse update(@PathVariable int id,@Valid @RequestBody UpdateRecordRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
