package com.ddr.back.repository;

import com.ddr.back.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    // You can define custom query methods here
}
