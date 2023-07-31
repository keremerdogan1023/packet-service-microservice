package com.kerem.packetservice.repository;

import com.kerem.packetservice.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Integer> {
}
