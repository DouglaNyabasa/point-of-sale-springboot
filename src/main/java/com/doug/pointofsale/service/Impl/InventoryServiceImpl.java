package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.payload.dto.InventoryDTO;
import com.doug.pointofsale.repository.InventoryRepository;
import com.doug.pointofsale.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {

        return null;
    }

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        return null;
    }

    @Override
    public void deleteInventory(Long id) {

    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        return null;
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        return null;
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        return List.of();
    }
}
