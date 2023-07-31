package com.kerem.packetservice.repository;

import com.kerem.packetservice.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastRepository extends JpaRepository<Podcast,Integer> {
}
