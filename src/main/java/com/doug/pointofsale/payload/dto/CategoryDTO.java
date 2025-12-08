package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.models.Store;

import lombok.*;

@Data
@Builder
public class CategoryDTO {

    private Long id;

    private String name;
    private Store store;
}
