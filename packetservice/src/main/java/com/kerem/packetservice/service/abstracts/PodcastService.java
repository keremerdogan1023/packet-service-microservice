package com.kerem.packetservice.service.abstracts;

import com.kerem.packetservice.service.dto.requests.create.CreatePodcastRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdatePodcastRequest;
import com.kerem.packetservice.service.dto.responses.create.CreatePodcastResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllPodcastsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetPodcastResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdatePodcastResponse;
import com.kerem.packetservice.entities.enums.State;

import java.util.List;

public interface PodcastService {
    CreatePodcastResponse add(CreatePodcastRequest request);
    UpdatePodcastResponse update(int id, UpdatePodcastRequest request);
    GetPodcastResponse getById(int id);
    List<GetAllPodcastsResponse> getAll();
    void delete(int id);
    void changeState(int id, State state);
}
