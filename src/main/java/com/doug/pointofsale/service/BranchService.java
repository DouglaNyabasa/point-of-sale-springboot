package com.doug.pointofsale.service;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO, User user) throws UserException;
    BranchDTO getBranchById(Long id,BranchDTO branchDTO,User user) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDTO> getAllBranchByStoreId(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;
}
