package com.doug.pointofsale.service;

import com.doug.pointofsale.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto);
    List<CategoryDTO> getAllCategories(Long storeId);
    CategoryDTO updateCategory(Long id,CategoryDTO dto);
    void deleteCategory(Long id);
}
