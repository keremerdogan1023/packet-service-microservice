package com.kerem.packetservicee.service.concretes;

import com.kerem.commonpackage.events.packet.create.AudiobookCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.enums.State;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.packetservicee.entities.Audiobook;
import com.kerem.packetservicee.repository.AudiobookRepository;
import com.kerem.packetservicee.service.abstracts.AudiobookService;
import com.kerem.packetservicee.service.dto.requests.create.CreateAudiobookRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateAudiobookRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllAudiobooksResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAudiobookResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateAudiobookResponse;
import com.kerem.packetservicee.service.rules.AudiobookBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class AudiobookManager implements AudiobookService {
    private final AudiobookRepository repository;
    private final ModelMapperService mapper;
    private final AudiobookBusinessRules rules;
    private final KafkaProducer producer;

    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\audiobooks\\";
    @Override
    public CreateAudiobookResponse add(CreateAudiobookRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Audiobook audiobook = mapper.forRequest().map(request,Audiobook.class);
        audiobook.setId(0);
        audiobook.setPacketId(1);
        audiobook.setState(State.ACTIVE);
        audiobook.setFilePath(filepath+ request.getName()+".mp3");
        var createdAudiobook = repository.save(audiobook);
        sendKafkaAudiobookCreatedEvent(createdAudiobook);
        CreateAudiobookResponse response = mapper.forResponse().map(audiobook, CreateAudiobookResponse.class);
        return response;
    }

    @Override
    public UpdateAudiobookResponse update(int id, UpdateAudiobookRequest request) {
        rules.checkIfAudiobookexists(id);
        Audiobook audiobook = mapper.forRequest().map(request, Audiobook.class);
        audiobook.setId(id);
        repository.save(audiobook);
        UpdateAudiobookResponse response = mapper.forResponse().map(audiobook, UpdateAudiobookResponse.class);
        return response;
    }

    @Override
    public GetAudiobookResponse getById(int id) {
        rules.checkIfAudiobookexists(id);
        Audiobook audiobook = repository.findById(id).orElseThrow();
        GetAudiobookResponse response = mapper.forResponse().map(audiobook, GetAudiobookResponse.class);
        return response;
    }

    @Override
    public List<GetAllAudiobooksResponse> getAll() {
        List<Audiobook> audiobooks =repository.findAll();
        List<GetAllAudiobooksResponse> response = audiobooks.stream()
                .map(audiobook -> mapper.forResponse().map(audiobook, GetAllAudiobooksResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfAudiobookexists(id);
        sendKafkaAudiobookDeletedEvent(id);
        repository.deleteById(id);

    }

    public void decodeMp3(CreateAudiobookRequest request){
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
    private void sendKafkaAudiobookCreatedEvent(Audiobook createdAudiobook){
        var event = mapper.forRequest().map(createdAudiobook, AudiobookCreatedEvent.class);
        producer.sendMessage(event,"audiobook-created");
    }
    private void sendKafkaAudiobookDeletedEvent(int id){
        producer.sendMessage(new AudiobookDeletedEvent(id),"audiobook-deleted");
    }
    //TODO: add updateEvent
}
