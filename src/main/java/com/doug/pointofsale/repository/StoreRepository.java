package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreAdminId(Long adminId);
}
