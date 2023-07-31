package com.kerem.packetservice.repository;

import com.kerem.packetservice.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Integer> {
}
