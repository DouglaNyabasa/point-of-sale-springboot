package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Inventory;
import com.doug.pointofsale.models.Product;
import com.doug.pointofsale.payload.dto.InventoryDTO;

public class InventoryMapper {

        public static InventoryDTO toDTO(Inventory inventory) {
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setId(inventory.getId());
            inventoryDTO.setBranch(inventory.getBranch());
            inventoryDTO.setProduct(inventoryDTO.getProduct());
            inventoryDTO.setProduct(ProductMapper.toDTO(inventory.getProduct()));
            inventoryDTO.setQuantity(inventory.getQuantity());
            return inventoryDTO;


        }

        public static Inventory toEntity(InventoryDTO inventoryDTO, Branch branch, Product product) {
            Inventory inventory = new Inventory();
            inventory.setBranch(branch);
            inventory.setProduct(product);
            inventory.setQuantity(inventoryDTO.getQuantity());
            return inventory;

        }
}
