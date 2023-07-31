package com.kerem.packetservice.service.concretes;

import com.kerem.packetservice.service.abstracts.AudiobookService;
import com.kerem.packetservice.service.dto.requests.create.CreateAudiobookRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateAudiobookRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateAudiobookResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllAudiobooksResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAudiobookResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateAudiobookResponse;
import com.kerem.packetservice.service.rules.AudiobookBusinessRules;
import com.kerem.packetservice.entities.Audiobook;
import com.kerem.packetservice.entities.enums.State;
import com.kerem.packetservice.repository.AudiobookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class AudiobookManager implements AudiobookService {
    private final AudiobookRepository repository;
    private final ModelMapper mapper;
    private final AudiobookBusinessRules rules;

    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\audiobooks\\";



    @Override
    public CreateAudiobookResponse add(CreateAudiobookRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Audiobook audiobook = mapper.map(request,Audiobook.class);
        audiobook.setId(0);
        audiobook.setPacketId(1);
        audiobook.setState(State.ACTIVE);
        audiobook.setFilePath(filepath+ request.getName()+".mp3");
        repository.save(audiobook);
        CreateAudiobookResponse response = mapper.map(audiobook, CreateAudiobookResponse.class);
        return response;
    }

    @Override
    public UpdateAudiobookResponse update(int id, UpdateAudiobookRequest request) {
        rules.checkIfAudiobookexists(id);
        Audiobook audiobook = mapper.map(request, Audiobook.class);
        audiobook.setId(id);
        repository.save(audiobook);
        UpdateAudiobookResponse response = mapper.map(audiobook, UpdateAudiobookResponse.class);
        return response;
    }

    @Override
    public GetAudiobookResponse getById(int id) {
        rules.checkIfAudiobookexists(id);
        Audiobook audiobook = repository.findById(id).orElseThrow();
        GetAudiobookResponse response = mapper.map(audiobook, GetAudiobookResponse.class);
        return response;
    }

    @Override
    public List<GetAllAudiobooksResponse> getAll() {
        List<Audiobook> audiobooks =repository.findAll();
        List<GetAllAudiobooksResponse> response = audiobooks.stream()
                .map(audiobook -> mapper.map(audiobook, GetAllAudiobooksResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfAudiobookexists(id);
        repository.deleteById(id);

    }

    @Override
    public void changeState(int id, State state) {

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
}
