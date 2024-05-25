package com.ddr.back.repository;

import com.ddr.back.entity.AirportCityCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportCityCountryRepository extends JpaRepository<AirportCityCountry, Long> {
    // You can define custom query methods here
}
