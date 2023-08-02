package com.kerem.packetservicee.repository;

import com.kerem.packetservicee.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Integer> {
}
