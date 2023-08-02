package com.kerem.packetservicee.repository;

import com.kerem.packetservicee.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Integer> {
}
