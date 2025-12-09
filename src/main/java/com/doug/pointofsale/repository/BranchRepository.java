package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    public List<Branch> findByStoreId(Long storeId);
}
