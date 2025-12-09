package com.doug.pointofsale.service;

import com.doug.pointofsale.payload.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(InventoryDTO inventoryDTO);
    void deleteInventory(Long id);
    InventoryDTO getInventoryById(Long id);
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDTO> getAllInventoryByBranchId(Long branchId);
}
