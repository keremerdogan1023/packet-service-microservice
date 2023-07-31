package com.kerem.packetservice.repository;

import com.kerem.packetservice.entities.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookRepository extends JpaRepository<Audiobook,Integer> {

}
