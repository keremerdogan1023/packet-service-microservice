package com.kerem.packetservice.service.abstracts;

import com.kerem.packetservice.service.dto.requests.create.CreateRecordRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateRecordRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateRecordResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllRecordsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetRecordResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateRecordResponse;

import java.util.List;

public interface RecordService {
    CreateRecordResponse add(CreateRecordRequest request);
    UpdateRecordResponse update(int id, UpdateRecordRequest request);
    GetRecordResponse getById(int id);
    List<GetAllRecordsResponse> getAll();
    void delete(int id);
    //void changeState(int id, State state);
}
