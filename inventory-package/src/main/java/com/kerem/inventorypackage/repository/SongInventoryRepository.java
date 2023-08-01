package com.kerem.inventorypackage.repository;

import com.kerem.inventorypackage.entities.SongInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongInventoryRepository extends JpaRepository<SongInventory,Integer> {
}
