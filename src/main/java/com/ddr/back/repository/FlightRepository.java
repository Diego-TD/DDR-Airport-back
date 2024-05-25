package com.ddr.back.repository;

import com.ddr.back.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // You can define custom query methods here
}
