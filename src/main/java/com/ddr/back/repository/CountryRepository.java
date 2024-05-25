package com.ddr.back.repository;

import com.ddr.back.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    // You can define custom query methods here
}
