package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
