package com.doug.pointofsale.service;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO , User user);
    ProductDTO updateProduct(Long id ,ProductDTO productDTO , User user);
    List<ProductDTO> getAllProductsByStoreId(Long storeId);
    void deleteProduct(Long id, User user);
    List<ProductDTO> searchBYKeyword(Long storeId, String keyword);
}
