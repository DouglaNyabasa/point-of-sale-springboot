package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.mapper.BranchMapper;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.BranchDTO;
import com.doug.pointofsale.repository.BranchRepository;
import com.doug.pointofsale.repository.StoreRepository;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.BranchService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    public BranchServiceImpl(BranchRepository branchRepository, StoreRepository storeRepository, UserRepository userRepository, UserService userService) {
        this.branchRepository = branchRepository;
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO, User user) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDTO,store);
        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDTO(savedBranch);
    }



    @Override
    public BranchDTO getBranchById(Long id, BranchDTO branchDTO, User user) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch does not exist !!!!")
        );
        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setPhone(branchDTO.getPhone());
        existing.setAddress(branchDTO.getAddress());
        existing.setOpenTime(branchDTO.getOpenTime());
        existing.setCloseTime(branchDTO.getCloseTime());
        existing.setUpdatedAt(branchDTO.getUpdatedAt());

        Branch updatedBranch = branchRepository.save(existing);

        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch does not exist !!!!")
        );
        branchRepository.delete(existing);
    }

    @Override
    public List<BranchDTO> getAllBranchByStoreId(Long storeId) {

        List<Branch> branches = branchRepository.findByStoreId(storeId);
         return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch does not exist !!!!")
        );

        return BranchMapper.toDTO(existing);
    }
}
