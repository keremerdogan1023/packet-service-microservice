package com.kerem.packetservice.service.abstracts;

import com.kerem.packetservice.service.dto.requests.create.CreateSongRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateSongRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateSongResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllSongsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetSongResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateSongResponse;

import java.util.List;

public interface SongService {
    CreateSongResponse add(CreateSongRequest request);
    UpdateSongResponse update(int id, UpdateSongRequest request);
    GetSongResponse getById(int id);
    List<GetAllSongsResponse> getAll();
    void delete(int id);
}
