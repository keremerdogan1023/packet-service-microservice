package com.kerem.packetservicee.service.concretes;

import com.kerem.commonpackage.events.packet.create.PodcastCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.events.packet.delete.PodcastDeletedEvent;
import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.enums.State;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.packetservicee.entities.Podcast;
import com.kerem.packetservicee.repository.PodcastRepository;
import com.kerem.packetservicee.service.abstracts.PodcastService;
import com.kerem.packetservicee.service.dto.requests.create.CreatePodcastRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdatePodcastRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreatePodcastResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllPodcastsResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetPodcastResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdatePodcastResponse;
import com.kerem.packetservicee.service.rules.PodcastBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class PodcastManager implements PodcastService {
    private final PodcastRepository repository;
    private final ModelMapperService mapper;
    private final PodcastBusinessRules rules;
    private final KafkaProducer producer;
    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\podcasts\\";

    @Override
    public CreatePodcastResponse add(CreatePodcastRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Podcast podcast = mapper.forRequest().map(request,Podcast.class);
        podcast.setId(0);
        podcast.setPacketId(2);
        podcast.setState(State.ACTIVE);
        podcast.setFilePath(filepath+ request.getName()+".mp3");
        var createdPodcast = repository.save(podcast);
        sendKafkaPodcastCreatedEvent(createdPodcast);
        CreatePodcastResponse response = mapper.forResponse().map(podcast, CreatePodcastResponse.class);
        return response;
    }

    @Override
    public UpdatePodcastResponse update(int id, UpdatePodcastRequest request) {
        Podcast podcast = mapper.forRequest().map(request, Podcast.class);
        podcast.setId(id);
        repository.save(podcast);
        UpdatePodcastResponse response = mapper.forResponse().map(podcast, UpdatePodcastResponse.class);
        return response;
    }

    @Override
    public GetPodcastResponse getById(int id) {
        Podcast podcast = repository.findById(id).orElseThrow();
        GetPodcastResponse response = mapper.forResponse().map(podcast, GetPodcastResponse.class);
        return response;
    }

    @Override
    public List<GetAllPodcastsResponse> getAll() {
        List<Podcast> podcasts =repository.findAll();
        List<GetAllPodcastsResponse> response = podcasts.stream()
                .map(podcast -> mapper.forResponse().map(podcast, GetAllPodcastsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfPodcastexists(id);
        sendKafkaPodcastDeletedEvent(id);
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
    private void sendKafkaPodcastCreatedEvent(Podcast createdPodcast){
        var event = mapper.forRequest().map(createdPodcast, PodcastCreatedEvent.class);
        producer.sendMessage(event,"podcast-created");
    }
    private void sendKafkaPodcastDeletedEvent(int id){
        producer.sendMessage(new PodcastDeletedEvent(id),"podcast-deleted");
    }
    //TODO: add updateEvent
}
