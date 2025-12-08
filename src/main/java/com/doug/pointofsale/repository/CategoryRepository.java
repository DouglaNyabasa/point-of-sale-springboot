package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List <Category> findByStoreId(Long storeId);
}
