package com.doug.pointofsale.controller;

import com.doug.pointofsale.models.Inventory;
import com.doug.pointofsale.payload.dto.InventoryDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {


    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));

    }
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id,@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> updateInventory(@PathVariable Long id) throws Exception {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted the inventory");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByProductAndBranchId(@PathVariable Long branchId ,@PathVariable Long productId) throws Exception {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(branchId,productId));

    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));

    }
}
