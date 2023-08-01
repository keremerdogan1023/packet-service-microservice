package com.kerem.inventorypackage.repository;

import com.kerem.inventorypackage.entities.AudiobookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookInventoryRepository extends JpaRepository<AudiobookInventory,Integer> {
}
