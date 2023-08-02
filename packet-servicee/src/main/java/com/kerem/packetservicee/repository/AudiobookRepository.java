package com.kerem.packetservicee.repository;

import com.kerem.packetservicee.entities.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookRepository extends JpaRepository<Audiobook,Integer> {
}
