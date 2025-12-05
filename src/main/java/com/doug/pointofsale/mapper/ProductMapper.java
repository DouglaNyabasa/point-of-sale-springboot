
package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Product;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.payload.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCode(product.getCode());
        productDTO.setMrp(product.getMrp());
        productDTO.setSellingPrice(product.getSellingPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setTaxRate(product.getTaxRate());
        productDTO.setStoreId(product.getStore().getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());

        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO, Store store) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setTaxRate(productDTO.getTaxRate());
        product.setBrand(productDTO.getBrand());
        product.setImageUrl(productDTO.getImageUrl());
        product.setStore(store);

        return product;
    }
}
