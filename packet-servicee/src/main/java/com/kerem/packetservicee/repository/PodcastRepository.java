package com.kerem.packetservicee.repository;

import com.kerem.packetservicee.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastRepository extends JpaRepository<Podcast,Integer> {
}
