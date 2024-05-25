package com.ddr.back.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer totalCapacity; // the total capacity of the airplane
    @Column(nullable = false)
    private Integer totalStorage; //luggage total capacity
    @Column(nullable = false)
    private Boolean fullCapacity;
    @Column(nullable = false)
    private Boolean fullStorage;

    public Airplane() {

    }

    public Airplane(Long id, String name, Integer totalCapacity, Integer totalStorage, Boolean fullCapacity, Boolean fullStorage) {
        this.id = id;
        this.name = name;
        this.totalCapacity = totalCapacity;
        this.totalStorage = totalStorage;
        this.fullCapacity = fullCapacity;
        this.fullStorage = fullStorage;
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

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(Integer totalStorage) {
        this.totalStorage = totalStorage;
    }

    public Boolean getFullCapacity() {
        return fullCapacity;
    }

    public void setFullCapacity(Boolean fullCapacity) {
        this.fullCapacity = fullCapacity;
    }

    public Boolean getFullStorage() {
        return fullStorage;
    }

    public void setFullStorage(Boolean fullStorage) {
        this.fullStorage = fullStorage;
    }
}
