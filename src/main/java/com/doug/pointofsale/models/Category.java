package com.doug.pointofsale.models;


import jakarta.persistence.*;
import lombok.*;
@Entity

@EqualsAndHashCode
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToOne
    private Store store;


    public Category( Store store,String name) {
        this.store = store;
        this.name = name;
    }

    public Category() {

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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
