package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.payload.dto.BranchDTO;

public class BranchMapper {
    public static BranchDTO toDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(branch.getId());
        branchDTO.setName(branch.getName());
        branchDTO.setAddress(branch.getAddress());
        branchDTO.setPhone(branch.getPhone());
        branchDTO.setEmail(branch.getEmail());
        branchDTO.setCloseTime(branch.getCloseTime());
        branchDTO.setWorkingDays(branch.getWorkingDays());
        branchDTO.setStoreId(branch.getStore() != null ? branch.getStore().getId() : null);
        branchDTO.setOpenTime(branch.getOpenTime());
        branchDTO.setUpdatedAt(branch.getUpdatedAt());
        branchDTO.setCreatedAt(branch.getCreatedAt());
        return branchDTO;

    }

    public static Branch toEntity(BranchDTO branchDTO, Store store) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setStore(store);
        branch.setPhone(branchDTO.getPhone());
        branch.setEmail(branchDTO.getEmail());
        branch.setCloseTime(branchDTO.getCloseTime());
        branch.setWorkingDays(branchDTO.getWorkingDays());
        branch.setOpenTime(branchDTO.getOpenTime());
        branch.setCreatedAt(branchDTO.getCreatedAt());
        branch.setUpdatedAt(branchDTO.getUpdatedAt());
        return branch;
    }
}
