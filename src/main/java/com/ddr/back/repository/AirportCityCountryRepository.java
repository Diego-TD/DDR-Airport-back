package com.ddr.back.repository;

import com.ddr.back.entity.AirportCityCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportCityCountryRepository extends JpaRepository<AirportCityCountry, Long> {
    @Query("SELECT a FROM AirportCityCountry a WHERE a.country.id = :countryId")
    List<AirportCityCountry> findAllByCountryId(@Param("countryId") Long countryId);

    @Query("SELECT a FROM AirportCityCountry a WHERE a.city.id = :cityId")
    List<AirportCityCountry> findAllByCityId(@Param("cityId") Long cityId);


}
