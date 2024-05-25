package com.ddr.back.entity;

import jakarta.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public City(){

    }

    public City (Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId (){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName (){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
