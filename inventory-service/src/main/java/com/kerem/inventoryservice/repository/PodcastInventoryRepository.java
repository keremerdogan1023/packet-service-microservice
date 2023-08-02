package com.kerem.inventoryservice.repository;

import com.kerem.inventoryservice.entities.PodcastInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastInventoryRepository extends JpaRepository<PodcastInventory,Integer> {
}
