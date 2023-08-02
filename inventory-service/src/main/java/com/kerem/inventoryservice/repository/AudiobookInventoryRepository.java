package com.kerem.inventoryservice.repository;

import com.kerem.inventoryservice.entities.AudiobookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookInventoryRepository extends JpaRepository<AudiobookInventory,Integer> {
}
