package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.mapper.ProductMapper;
import com.doug.pointofsale.models.Product;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ProductDTO;
import com.doug.pointofsale.repository.ProductRepository;
import com.doug.pointofsale.repository.StoreRepository;
import com.doug.pointofsale.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductServiceImpl(ProductRepository productRepository, StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
        Store store = storeRepository.findById(productDTO.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );
        Product product = ProductMapper.toEntity(productDTO,store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new  Exception("Product not found")
        );
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBrand(productDTO.getBrand());
        product.setCode(productDTO.getCode());
        product.setImageUrl(productDTO.getImageUrl());
        product.setBrand(productDTO.getBrand());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setTaxRate(productDTO.getTaxRate());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> getAllProductsByStoreId(Long storeId) {

        List <Product> products = productRepository.findByStoreId(storeId);
        return  products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found !!")
        );
        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> searchBYKeyword(Long storeId, String keyword) {
        List <Product> products = productRepository.searchByKeyword(storeId,keyword);
        return  products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
