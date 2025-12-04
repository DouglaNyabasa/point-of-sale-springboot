package com.doug.pointofsale.service;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO , User user) throws Exception;
    ProductDTO updateProduct(Long id ,ProductDTO productDTO , User user) throws Exception;
    List<ProductDTO> getAllProductsByStoreId(Long storeId);
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDTO> searchByKeyword(Long storeId, String keyword);
}
