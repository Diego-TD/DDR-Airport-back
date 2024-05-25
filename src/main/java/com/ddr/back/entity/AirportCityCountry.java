package com.ddr.back.entity;

import jakarta.persistence.*;

@Entity
public class AirportCityCountry {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Airport airport;
    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    public AirportCityCountry(){

    }

    public AirportCityCountry(Long id, Airport airport, City city, Country country) {
        this.id = id;
        this.airport = airport;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
