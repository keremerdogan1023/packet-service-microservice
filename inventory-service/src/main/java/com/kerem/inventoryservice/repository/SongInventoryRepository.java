package com.kerem.inventoryservice.repository;

import com.kerem.inventoryservice.entities.SongInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongInventoryRepository extends JpaRepository<SongInventory,Integer> {
}
