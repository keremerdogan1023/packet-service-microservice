package com.kerem.packetservice.service.concretes;

import com.kerem.packetservice.service.abstracts.RecordService;
import com.kerem.packetservice.service.dto.requests.create.CreateRecordRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateRecordRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateRecordResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllRecordsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetRecordResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateRecordResponse;
import com.kerem.packetservice.service.rules.RecordBusinessRules;
import com.kerem.packetservice.entities.Record;
import com.kerem.packetservice.entities.enums.State;
import com.kerem.packetservice.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordManager implements RecordService {
    private final RecordRepository repository;
    private final ModelMapper mapper;
    private final RecordBusinessRules rules;
    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\records\\";
    @Override
    public CreateRecordResponse add(CreateRecordRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Record record = mapper.map(request,Record.class);
        record.setId(0);
        record.setPacketId(3);
        record.setState(State.ACTIVE);
        record.setFilePath(filepath+ request.getName()+".mp3");
        repository.save(record);
        CreateRecordResponse response = mapper.map(record, CreateRecordResponse.class);
        return response;
    }

    @Override
    public UpdateRecordResponse update(int id, UpdateRecordRequest request) {
        rules.checkIfRecordexists(id);
        Record record = mapper.map(request, Record.class);
        record.setId(id);
        repository.save(record);
        UpdateRecordResponse response = mapper.map(record, UpdateRecordResponse.class);
        return response;
    }

    @Override
    public GetRecordResponse getById(int id) {
        rules.checkIfRecordexists(id);
        Record record = repository.findById(id).orElseThrow();
        GetRecordResponse response = mapper.map(record, GetRecordResponse.class);
        return response;
    }

    @Override
    public List<GetAllRecordsResponse> getAll() {
        List<Record> records =repository.findAll();
        List<GetAllRecordsResponse> response = records.stream()
                .map(record -> mapper.map(record, GetAllRecordsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRecordexists(id);
        repository.deleteById(id);
    }
    public void decodeMp3(CreateRecordRequest request){
        byte[] decodedMp3 = Base64.getDecoder().decode(request.getFile());
        try( OutputStream stream = new FileOutputStream(filepath+ request.getName()+".mp3") )
        {
            stream.write(decodedMp3);
        }
        catch (Exception e)
        {
            System.err.println("Couldn't write to file...");
        }
    }
}
