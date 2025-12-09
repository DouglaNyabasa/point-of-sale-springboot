package com.doug.pointofsale.controller;


import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.payload.dto.BranchDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create")
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {
        BranchDTO createBranch = branchService.createBranch(branchDTO);
        return  ResponseEntity.ok(createBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById( @PathVariable Long id) throws Exception {
        BranchDTO getBranch = branchService.getBranchById(id);
        return  ResponseEntity.ok(getBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchByStoreId(@PathVariable Long storeId) throws Exception {
        List<BranchDTO>  getBranch = branchService.getAllBranchByStoreId(storeId);
        return  ResponseEntity.ok(getBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch( @PathVariable Long id, @RequestBody BranchDTO branchDTO) throws Exception {
        BranchDTO updateBranch = branchService.updateBranch(id,branchDTO);
        return  ResponseEntity.ok(updateBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }



}
