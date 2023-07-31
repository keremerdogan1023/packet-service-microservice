package com.kerem.packetservice.service.concretes;

import com.kerem.packetservice.service.abstracts.PodcastService;
import com.kerem.packetservice.service.dto.requests.create.CreatePodcastRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdatePodcastRequest;
import com.kerem.packetservice.service.dto.responses.create.CreatePodcastResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllPodcastsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetPodcastResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdatePodcastResponse;
import com.kerem.packetservice.service.rules.PodcastBusinessRules;
import com.kerem.packetservice.entities.Podcast;
import com.kerem.packetservice.entities.enums.State;
import com.kerem.packetservice.repository.PodcastRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class PodcastManager implements PodcastService {
    private final PodcastRepository repository;
    private final ModelMapper mapper;
    private final PodcastBusinessRules rules;
    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\podcasts\\";

    @Override
    public CreatePodcastResponse add(CreatePodcastRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Podcast podcast = mapper.map(request,Podcast.class);
        podcast.setId(0);
        podcast.setPacketId(2);
        podcast.setState(State.ACTIVE);
        podcast.setFilePath(filepath+ request.getName()+".mp3");
        repository.save(podcast);
        CreatePodcastResponse response = mapper.map(podcast, CreatePodcastResponse.class);
        return response;
    }

    @Override
    public UpdatePodcastResponse update(int id, UpdatePodcastRequest request) {
        Podcast podcast = mapper.map(request, Podcast.class);
        podcast.setId(id);
        repository.save(podcast);
        UpdatePodcastResponse response = mapper.map(podcast, UpdatePodcastResponse.class);
        return response;
    }

    @Override
    public GetPodcastResponse getById(int id) {
        Podcast podcast = repository.findById(id).orElseThrow();
        GetPodcastResponse response = mapper.map(podcast, GetPodcastResponse.class);
        return response;
    }

    @Override
    public List<GetAllPodcastsResponse> getAll() {
        List<Podcast> podcasts =repository.findAll();
        List<GetAllPodcastsResponse> response = podcasts.stream()
                .map(podcast -> mapper.map(podcast, GetAllPodcastsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);

    }

    @Override
    public void changeState(int id, State state) {

    }
    public void decodeMp3(CreatePodcastRequest request){
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
