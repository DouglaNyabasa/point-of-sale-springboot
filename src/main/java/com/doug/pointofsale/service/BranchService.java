package com.doug.pointofsale.service;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO, User user);
    BranchDTO getBranchById(Long id,BranchDTO branchDTO,User user);
    BranchDTO deleteBranch(Long id);
    List<BranchDTO> getAllBranchByStoreId(Long storeId);
    BranchDTO getBranchById(Long id);
}
