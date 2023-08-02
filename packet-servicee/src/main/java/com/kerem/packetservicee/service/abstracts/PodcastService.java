package com.kerem.packetservicee.service.abstracts;

import com.kerem.commonpackage.utils.enums.State;
import com.kerem.packetservicee.service.dto.requests.create.CreatePodcastRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdatePodcastRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreatePodcastResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllPodcastsResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetPodcastResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdatePodcastResponse;

import java.util.List;

public interface PodcastService {
    CreatePodcastResponse add(CreatePodcastRequest request);
    UpdatePodcastResponse update(int id, UpdatePodcastRequest request);
    GetPodcastResponse getById(int id);
    List<GetAllPodcastsResponse> getAll();
    void delete(int id);
    void changeState(int id, State state);
}
