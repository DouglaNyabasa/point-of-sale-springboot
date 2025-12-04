package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Product;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.payload.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .taxRate(product.getTaxRate())
                .storeId(product.getId())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getCreatedAt())
                .build();




    }

    public static Product toEntity(ProductDTO productDTO, Store store) {
       return Product.builder()
               .name(productDTO.getName())
               .code(productDTO.getCode())
               .description(productDTO.getDescription())
               .mrp(productDTO.getMrp())
               .sellingPrice(productDTO.getSellingPrice())
               .taxRate(productDTO.getTaxRate())
               .brand(productDTO.getBrand())
               .imageUrl(productDTO.getImageUrl())
               .build();
    }
}
