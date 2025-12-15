package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
