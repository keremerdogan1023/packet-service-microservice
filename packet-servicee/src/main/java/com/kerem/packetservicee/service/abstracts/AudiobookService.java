package com.kerem.packetservicee.service.abstracts;

import com.kerem.packetservicee.service.dto.requests.create.CreateAudiobookRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateAudiobookRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllAudiobooksResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateAudiobookResponse;

import java.util.List;

public interface AudiobookService {
    CreateAudiobookResponse add(CreateAudiobookRequest request);
    UpdateAudiobookResponse update(int id, UpdateAudiobookRequest request);
    GetAudiobookResponse getById(int id);
    List<GetAllAudiobooksResponse> getAll();
    void delete(int id);
}
