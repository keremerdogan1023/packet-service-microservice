package com.kerem.packetservicee.api.controllers;

import com.kerem.packetservicee.service.abstracts.AudiobookService;
import com.kerem.packetservicee.service.dto.requests.create.CreateAudiobookRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateAudiobookRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllAudiobooksResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateAudiobookResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/audiobooks")
public class AudiobookController {
    private final AudiobookService service;
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAudiobookResponse add(@Valid @RequestBody CreateAudiobookRequest request) {
        return service.add(request);
    }
    @GetMapping
    public List<GetAllAudiobooksResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAudiobookResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateAudiobookResponse update(@PathVariable int id, @Valid @RequestBody UpdateAudiobookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
