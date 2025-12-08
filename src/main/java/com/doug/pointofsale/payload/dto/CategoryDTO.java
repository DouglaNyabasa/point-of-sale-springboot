package com.doug.pointofsale.payload.dto;



public class CategoryDTO {

    private Long id;

    private String name;
    private Long storeId;

    public CategoryDTO( String name, Long storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
