package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.StoreDTO;
import com.doug.pointofsale.repository.StoreRepository;
import com.doug.pointofsale.service.StoreService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    public StoreServiceImpl(StoreRepository storeRepository, UserService userService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Override
    public StoreDTO createStore(StoreDTO storeDto, User user) {
        return null;
    }

    @Override
    public StoreDTO getStoreById(Long id) {
        return null;
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return List.of();
    }

    @Override
    public Store getStoreByAdmin() {
        return null;
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDto) {
        return null;
    }

    @Override
    public StoreDTO deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDTO getStoreByEmployee() {
        return null;
    }
}
