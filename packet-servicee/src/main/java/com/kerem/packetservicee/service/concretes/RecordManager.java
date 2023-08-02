package com.kerem.packetservicee.service.concretes;

import com.kerem.commonpackage.events.packet.create.RecordCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.events.packet.delete.RecordDeletedEvent;
import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.enums.State;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.packetservicee.entities.Record;
import com.kerem.packetservicee.repository.RecordRepository;
import com.kerem.packetservicee.service.abstracts.RecordService;
import com.kerem.packetservicee.service.dto.requests.create.CreateRecordRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateRecordRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateRecordResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllRecordsResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetRecordResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateRecordResponse;
import com.kerem.packetservicee.service.rules.RecordBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordManager implements RecordService {
    private final RecordRepository repository;
    private final ModelMapperService mapper;
    private final RecordBusinessRules rules;
    private final KafkaProducer producer;


    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\records\\";
    @Override
    public CreateRecordResponse add(CreateRecordRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Record record = mapper.forRequest().map(request,Record.class);
        record.setId(0);
        record.setPacketId(3);
        record.setState(State.ACTIVE);
        record.setFilePath(filepath+ request.getName()+".mp3");
        var createdRecord = repository.save(record);
        sendKafkaRecordCreatedEvent(createdRecord);
        CreateRecordResponse response = mapper.forResponse().map(record, CreateRecordResponse.class);
        return response;
    }

    @Override
    public UpdateRecordResponse update(int id, UpdateRecordRequest request) {
        rules.checkIfRecordexists(id);
        Record record = mapper.forRequest().map(request, Record.class);
        record.setId(id);
        repository.save(record);
        UpdateRecordResponse response = mapper.forResponse().map(record, UpdateRecordResponse.class);
        return response;
    }

    @Override
    public GetRecordResponse getById(int id) {
        rules.checkIfRecordexists(id);
        Record record = repository.findById(id).orElseThrow();
        GetRecordResponse response = mapper.forResponse().map(record, GetRecordResponse.class);
        return response;
    }

    @Override
    public List<GetAllRecordsResponse> getAll() {
        List<Record> records =repository.findAll();
        List<GetAllRecordsResponse> response = records.stream()
                .map(record -> mapper.forResponse().map(record, GetAllRecordsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRecordexists(id);
        sendKafkaRecordDeletedEvent(id);
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
    private void sendKafkaRecordCreatedEvent(Record createdRecord){
        var event = mapper.forRequest().map(createdRecord, RecordCreatedEvent.class);
        producer.sendMessage(event,"record-created");
    }
    private void sendKafkaRecordDeletedEvent(int id){
        producer.sendMessage(new RecordDeletedEvent(id),"record-deleted");
    }
    //TODO: add updateEvent
}
