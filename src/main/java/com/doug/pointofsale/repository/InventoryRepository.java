package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
