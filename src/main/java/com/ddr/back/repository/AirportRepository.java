package com.ddr.back.repository;

import com.ddr.back.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    // You can define custom query methods here
}
