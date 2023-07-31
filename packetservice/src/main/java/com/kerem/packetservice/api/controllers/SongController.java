package com.kerem.packetservice.api.controllers;

import com.kerem.packetservice.service.abstracts.SongService;
import com.kerem.packetservice.service.dto.requests.create.CreateSongRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateSongRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateSongResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllSongsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetSongResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateSongResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/songs")
public class SongController {
    private final SongService service;
    @GetMapping
    public List<GetAllSongsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSongResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSongResponse add(@Valid @RequestBody CreateSongRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateSongResponse update(@PathVariable int id,@Valid @RequestBody UpdateSongRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
