package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Store;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.StoreDTO;

public class StoreMapper {

    public static StoreDTO toDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getId());
        storeDTO.setBrand(store.getBrand());
        storeDTO.setDescription(store.getDescription());
        storeDTO.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDTO.setStoreType(store.getStoreType());
        storeDTO.setContact(store.getContact());
        storeDTO.setStatus(store.getStatus());
        storeDTO.setUpdatedAt(store.getUpdatedAt());
        storeDTO.setCreatedAt(store.getCreatedAt());
        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO, User storeAdmin) {
        Store store = new Store();
        store.setId(storeDTO.getId());
        store.setBrand(storeDTO.getBrand());
        store.setDescription(storeDTO.getDescription());
        store.setStoreAdmin(storeAdmin);
        store.setStoreType(storeDTO.getStoreType());
        store.setContact(storeDTO.getContact());
        store.setStatus(storeDTO.getStatus());
        store.setUpdatedAt(storeDTO.getUpdatedAt());
        store.setCreatedAt(storeDTO.getCreatedAt());
        return store;
    }
}
