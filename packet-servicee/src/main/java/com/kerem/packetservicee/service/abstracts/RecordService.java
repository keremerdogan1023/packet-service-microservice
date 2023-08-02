package com.kerem.packetservicee.service.abstracts;

import com.kerem.packetservicee.service.dto.requests.create.CreateRecordRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateRecordRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateRecordResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllRecordsResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetRecordResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateRecordResponse;

import java.util.List;

public interface RecordService {
    CreateRecordResponse add(CreateRecordRequest request);
    UpdateRecordResponse update(int id, UpdateRecordRequest request);
    GetRecordResponse getById(int id);
    List<GetAllRecordsResponse> getAll();
    void delete(int id);
}
