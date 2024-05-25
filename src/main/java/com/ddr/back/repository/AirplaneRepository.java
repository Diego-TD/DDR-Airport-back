package com.ddr.back.repository;

import com.ddr.back.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    // You can define custom query methods here
}
