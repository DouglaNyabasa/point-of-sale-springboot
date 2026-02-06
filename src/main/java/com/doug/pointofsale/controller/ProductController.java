package com.doug.pointofsale.controller;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ProductDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.ProductService;
import com.doug.pointofsale.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.createProduct(productDTO,user));

    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getByStoreId(@PathVariable Long storeId, @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(productService.getAllProductsByStoreId(storeId));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(productService.updateProduct(id ,productDTO,user));

    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchByKeyword(@PathVariable Long storeId, @RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {

        return ResponseEntity.ok(productService.searchByKeyword(storeId,keyword));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity <ApiResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id ,user);

        ApiResponse apiResponse = new  ApiResponse();
        apiResponse.setMessage("Product successfully deleted");



        return ResponseEntity.ok(apiResponse);

    }
}
