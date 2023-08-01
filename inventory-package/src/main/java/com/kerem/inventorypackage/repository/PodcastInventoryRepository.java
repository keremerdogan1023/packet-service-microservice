package com.kerem.inventorypackage.repository;

import com.kerem.inventorypackage.entities.PodcastInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastInventoryRepository extends JpaRepository<PodcastInventory,Integer> {
}
