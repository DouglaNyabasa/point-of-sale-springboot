package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.mapper.CategoryMapper;
import com.doug.pointofsale.models.Category;
import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.CategoryDTO;
import com.doug.pointofsale.repository.CategoryRepository;
import com.doug.pointofsale.repository.StoreRepository;
import com.doug.pointofsale.service.CategoryService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private  final StoreRepository storeRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService, StoreRepository storeRepository) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.storeRepository = storeRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );
        Category category = Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        return CategoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories(Long storeId) {
         List<Category> categories = categoryRepository.findByStoreId(storeId);
         return  categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("category not found !!")
        );
        User user = userService.getCurrentUser();
        category.setName(dto.getName());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("category not found !!")
        );
        User user = userService.getCurrentUser();
        categoryRepository.delete(category);

    }

    private void checkAuthority(User user,Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.getStore().equals(store.getStoreAdmin());

        if (!(isAdmin && isSameStore) && !isManager) {
            throw new Exception("you don't have permissions to manage this category ");

        }
    }
}
