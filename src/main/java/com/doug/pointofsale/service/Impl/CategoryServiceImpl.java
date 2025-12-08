package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.payload.dto.CategoryDTO;
import com.doug.pointofsale.repository.CategoryRepository;
import com.doug.pointofsale.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories(Long storeId) {
        return List.of();
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
