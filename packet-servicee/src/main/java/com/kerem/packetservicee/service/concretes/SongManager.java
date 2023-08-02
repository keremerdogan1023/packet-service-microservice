package com.kerem.packetservicee.service.concretes;

import com.kerem.commonpackage.events.packet.create.SongCreatedEvent;
import com.kerem.commonpackage.events.packet.delete.AudiobookDeletedEvent;
import com.kerem.commonpackage.events.packet.delete.SongDeletedEvent;
import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.enums.State;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.packetservicee.entities.Song;
import com.kerem.packetservicee.repository.SongRepository;
import com.kerem.packetservicee.service.abstracts.SongService;
import com.kerem.packetservicee.service.dto.requests.create.CreateSongRequest;
import com.kerem.packetservicee.service.dto.requests.update.UpdateSongRequest;
import com.kerem.packetservicee.service.dto.responses.create.CreateSongResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetAllSongsResponse;
import com.kerem.packetservicee.service.dto.responses.get.GetSongResponse;
import com.kerem.packetservicee.service.dto.responses.update.UpdateSongResponse;
import com.kerem.packetservicee.service.rules.SongBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class SongManager implements SongService {
    private final SongRepository repository;
    private final ModelMapperService mapper;
    private final SongBusinessRules rules;
    private final KafkaProducer producer;

    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\songs\\";
    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Song song = mapper.forRequest().map(request,Song.class);
        song.setId(0);
        song.setPacketId(4);
        song.setState(State.ACTIVE);
        song.setFilePath(filepath+ request.getName()+".mp3");
        var createdSong = repository.save(song);
        sendKafkaSongCreatedEvent(createdSong);
        CreateSongResponse response = mapper.forResponse().map(song, CreateSongResponse.class);
        return response;
    }

    @Override
    public UpdateSongResponse update(int id, UpdateSongRequest request) {
        rules.checkIfSongexists(id);
        Song song = mapper.forRequest().map(request, Song.class);
        song.setId(id);
        repository.save(song);
        UpdateSongResponse response = mapper.forResponse().map(song, UpdateSongResponse.class);
        return response;
    }

    @Override
    public GetSongResponse getById(int id) {
        rules.checkIfSongexists(id);
        Song song = repository.findById(id).orElseThrow();
        GetSongResponse response = mapper.forResponse().map(song, GetSongResponse.class);
        return response;
    }

    @Override
    public List<GetAllSongsResponse> getAll() {
        List<Song> songs =repository.findAll();
        List<GetAllSongsResponse> response = songs.stream()
                .map(song -> mapper.forResponse().map(song, GetAllSongsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSongexists(id);
        sendKafkaSongDeletedEvent(id);
        repository.deleteById(id);
    }
    public void decodeMp3(CreateSongRequest request){
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
    private void sendKafkaSongCreatedEvent(Song createdSong){
        var event = mapper.forRequest().map(createdSong, SongCreatedEvent.class);
        producer.sendMessage(event,"song-created");
    }
    private void sendKafkaSongDeletedEvent(int id){
        producer.sendMessage(new SongDeletedEvent(id),"song-deleted");
    }
    //TODO: add updateEvent
}
