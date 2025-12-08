package com.doug.pointofsale.service;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws Exception;
    List<CategoryDTO> getAllCategories(Long storeId);
    CategoryDTO updateCategory(Long id,CategoryDTO dto) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
