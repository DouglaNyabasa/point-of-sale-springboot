package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ProductDTO;
import com.doug.pointofsale.repository.ProductRepository;
import com.doug.pointofsale.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public void deleteProduct(Long id, User user) {

    }

    @Override
    public List<ProductDTO> searchBYKeyword(Long storeId, String keyword) {
        return List.of();
    }
}
