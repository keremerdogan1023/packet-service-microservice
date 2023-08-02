package com.kerem.inventoryservice.repository;

import com.kerem.inventoryservice.entities.RecordInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordInventoryRepository extends JpaRepository<RecordInventory,Integer> {
}
