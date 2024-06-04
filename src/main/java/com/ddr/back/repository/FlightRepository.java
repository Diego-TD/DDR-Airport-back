package com.ddr.back.repository;

import com.ddr.back.dto.FlightDTO;
import com.ddr.back.entity.AirportCityCountry;
import com.ddr.back.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // You can define custom query methods here

    //select * from flight where arrival_airport_id = flightDTO.getArrivalAirportId() and departure_airport_id = flightDTO.getDepartureAirportId() and date = flightDTO.getDate;

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.id = :departureAirportId AND f.arrivalAirport.id = :arrivalAirportId AND f.date = :date")
    List<Flight> findOneWayFiltered(@Param("departureAirportId") Long departureAirportId, @Param("arrivalAirportId") Long arrivalAirportId, @Param("date") String date);

}
