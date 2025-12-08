package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
