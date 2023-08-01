package com.kerem.packetservice.service.concretes;

import com.kerem.packetservice.service.abstracts.SongService;
import com.kerem.packetservice.service.dto.requests.create.CreateSongRequest;
import com.kerem.packetservice.service.dto.requests.update.UpdateSongRequest;
import com.kerem.packetservice.service.dto.responses.create.CreateSongResponse;
import com.kerem.packetservice.service.dto.responses.get.GetAllSongsResponse;
import com.kerem.packetservice.service.dto.responses.get.GetSongResponse;
import com.kerem.packetservice.service.dto.responses.update.UpdateSongResponse;
import com.kerem.packetservice.service.rules.SongBusinessRules;
import com.kerem.packetservice.entities.Song;
import com.kerem.commonpackage.utils.enums.State;
import com.kerem.packetservice.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
@Service
@AllArgsConstructor
public class SongManager implements SongService {
    private final SongRepository repository;
    private final ModelMapper mapper;
    private final SongBusinessRules rules;
    private static final String filepath = "C:\\Users\\kerem\\Desktop\\mp3files\\songs\\";
    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        rules.isFileContentTypeSupported(request.getFile());
        decodeMp3(request);
        Song song = mapper.map(request,Song.class);
        song.setId(0);
        song.setPacketId(4);
        song.setState(State.ACTIVE);
        song.setFilePath(filepath+ request.getName()+".mp3");
        repository.save(song);
        CreateSongResponse response = mapper.map(song, CreateSongResponse.class);
        return response;
    }

    @Override
    public UpdateSongResponse update(int id, UpdateSongRequest request) {
        rules.checkIfSongexists(id);
        Song song = mapper.map(request, Song.class);
        song.setId(id);
        repository.save(song);
        UpdateSongResponse response = mapper.map(song, UpdateSongResponse.class);
        return response;
    }

    @Override
    public GetSongResponse getById(int id) {
        rules.checkIfSongexists(id);
        Song song = repository.findById(id).orElseThrow();
        GetSongResponse response = mapper.map(song, GetSongResponse.class);
        return response;
    }

    @Override
    public List<GetAllSongsResponse> getAll() {
        List<Song> songs =repository.findAll();
        List<GetAllSongsResponse> response = songs.stream()
                .map(song -> mapper.map(song, GetAllSongsResponse.class))
                .toList();
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSongexists(id);
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
}
