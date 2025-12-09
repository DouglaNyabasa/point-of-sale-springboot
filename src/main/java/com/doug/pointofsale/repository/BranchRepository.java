package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
