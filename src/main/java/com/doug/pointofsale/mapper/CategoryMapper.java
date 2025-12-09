package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Category;
import com.doug.pointofsale.payload.dto.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getStore() != null ? category.getStore().getId() : null
        );
    }
}
