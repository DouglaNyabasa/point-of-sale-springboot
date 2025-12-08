package com.doug.pointofsale.controller;


import com.doug.pointofsale.payload.dto.CategoryDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(
                categoryService.createCategory(categoryDTO)
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoryByStoreId(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(
                categoryService.getCategoriesByStoreId(storeId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.updateCategory(id, categoryDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted Category");
        return ResponseEntity.ok(
                apiResponse
        );
    }
}
