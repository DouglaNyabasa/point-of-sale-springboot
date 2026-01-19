package com.doug.pointofsale.controller;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.domain.StoreStatus;
import com.doug.pointofsale.mapper.StoreMapper;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.StoreDTO;
import com.doug.pointofsale.payload.response.ApiResponse;
import com.doug.pointofsale.service.StoreService;
import com.doug.pointofsale.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    public StoreController(StoreService storeService, UserService userService) {
        this.storeService = storeService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);


        return ResponseEntity.ok(storeService.createStore(storeDTO, user));

    }



    @GetMapping("/getAll")
    public ResponseEntity<List<StoreDTO>> getAllStore(@RequestHeader("Authorization") String jwt) throws UserException {
        return ResponseEntity.ok(storeService.getAllStores());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted store successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO, @RequestParam StoreStatus status) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id,@RequestBody StoreDTO storeDTO) throws Exception {
       return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws UserException {
        return ResponseEntity.ok(storeService.getStoreByEmployee());

    }
    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(@RequestHeader("Authorization") String jwt) throws UserException {
        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));

    }
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreById(id));

    }




}
