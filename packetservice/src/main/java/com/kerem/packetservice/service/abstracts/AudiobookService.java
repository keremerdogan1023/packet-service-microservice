package com.kerem.packetservice.service.abstracts;

import com.kerem.packetservice.service.dto.requests.create.CreateAudiobookRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateAudiobookRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateAudiobookResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllAudiobooksResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAudiobookResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateAudiobookResponse;
import com.kerem.packetservice.entities.enums.State;

import java.util.List;

public interface AudiobookService {
    CreateAudiobookResponse add(CreateAudiobookRequest request);
    UpdateAudiobookResponse update(int id,UpdateAudiobookRequest request);
    GetAudiobookResponse getById(int id);
    List<GetAllAudiobooksResponse> getAll();
    void delete(int id);
    void changeState(int id, State state);
}
