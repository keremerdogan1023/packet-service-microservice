package com.kerem.packetservice.api.controllers;

import com.kerem.packetservice.service.abstracts.PodcastService;
import com.kerem.packetservice.service.dto.requests.create.CreatePodcastRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdatePodcastRequest;
import com.kerem.packetservice.service.dto.responses.create.CreatePodcastResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllPodcastsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetPodcastResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdatePodcastResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/podcasts")
public class PodcastController {
    private final PodcastService service;
    @GetMapping
    public List<GetAllPodcastsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPodcastResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePodcastResponse add(@Valid  @RequestBody CreatePodcastRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePodcastResponse update(@PathVariable int id,@Valid @RequestBody UpdatePodcastRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
