package com.doug.pointofsale.service;

import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {


    StoreDTO createStore(StoreDTO storeDto, User user);
    StoreDTO getStoreById(Long id);
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin();
    StoreDTO updateStore(Long id ,StoreDTO storeDto);
    StoreDTO deleteStore(Long id);
    StoreDTO getStoreByEmployee();
}
