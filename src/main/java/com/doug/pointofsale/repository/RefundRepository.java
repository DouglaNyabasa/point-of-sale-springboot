package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
