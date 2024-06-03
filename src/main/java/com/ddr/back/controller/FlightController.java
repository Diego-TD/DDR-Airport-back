package com.ddr.back.controller;

import com.ddr.back.dto.FlightDTO;
import com.ddr.back.entity.*;
import com.ddr.back.repository.AirplaneRepository;
import com.ddr.back.repository.AirportRepository;
import com.ddr.back.repository.FlightRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {
    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirportRepository airportRepository;

    public FlightController(FlightRepository flightRepository, AirplaneRepository airplaneRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.airportRepository = airportRepository;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights() {
        try {
            List<Flight> list = flightRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<Optional<Flight>> getFlight(@PathVariable Long id) {
        try {
            if (!flightRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(flightRepository.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/flight")
    public ResponseEntity<?> createFlight(@Nonnull @RequestBody FlightDTO dto) {
        try {

            Optional<Airplane> airplaneOpt = airplaneRepository.findById(dto.getAirplaneId());
            Optional<Airport> departureAirportOpt = airportRepository.findById(dto.getDepartureAirportId());
            Optional<Airport> arrivalAirportOpt = airportRepository.findById(dto.getArrivalAirportId());

            if (airplaneOpt.isEmpty() || departureAirportOpt.isEmpty() || arrivalAirportOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid airplane, departure airport, or arrival airport IDs");
            }

            Flight flight = new Flight();
            flight.setAirplane(airplaneOpt.get());
            flight.setDepartureAirport(departureAirportOpt.get());
            flight.setArrivalAirport(arrivalAirportOpt.get());
            flight.setDate(dto.getDate());
            flight.setTime(dto.getTime());
            flight.setPrice(generateFlightPrice(flight));

            flightRepository.save(flight);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    @PutMapping("/flight/{id}")
    public ResponseEntity<?> updateFlight(@RequestBody FlightDTO dto, @PathVariable Long id) {
        try {

            Optional<Flight> existingEntityOpt = flightRepository.findById(id);
            if (existingEntityOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            Flight existingEntity = existingEntityOpt.get();

            Optional<Airplane> airplaneOpt = airplaneRepository.findById(dto.getAirplaneId());
            Optional<Airport> departureAirportOpt = airportRepository.findById(dto.getDepartureAirportId());
            Optional<Airport> arrivalAirportOpt = airportRepository.findById(dto.getArrivalAirportId());


            if (airplaneOpt.isEmpty() || departureAirportOpt.isEmpty() || arrivalAirportOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid airplane, departure airport, or arrival airport IDs");
            }

            existingEntity.setAirplane(airplaneOpt.get());
            existingEntity.setDepartureAirport(departureAirportOpt.get());
            existingEntity.setArrivalAirport(arrivalAirportOpt.get());
            existingEntity.setDate(dto.getDate());
            existingEntity.setTime(dto.getTime());

            flightRepository.save(existingEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update");
        }
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("ID cannot be null");
        }

        try {
            if (!flightRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            flightRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
        }
    }

    public Double generateFlightPrice(Flight flight){
        Double price;
        Airport departureAirport = flight.getDepartureAirport();
        Airport arrivalAirport = flight.getArrivalAirport();

        //calculate price in base of distance

        price = 1500.0;

        return price;
    }
}