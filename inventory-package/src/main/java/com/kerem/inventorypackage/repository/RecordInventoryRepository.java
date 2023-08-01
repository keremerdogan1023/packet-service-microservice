package com.kerem.inventorypackage.repository;

import com.kerem.inventorypackage.entities.RecordInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordInventoryRepository extends JpaRepository<RecordInventory,Integer> {
}
