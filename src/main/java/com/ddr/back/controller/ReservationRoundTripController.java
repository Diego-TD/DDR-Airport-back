package com.ddr.back.controller;

import com.ddr.back.dto.ReservationRoundTripDTO;
import com.ddr.back.entity.*;
import com.ddr.back.repository.FlightRepository;
import com.ddr.back.repository.ReservationRoundTripRepository;
import com.ddr.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationRoundTripController {

    private final ReservationRoundTripRepository reservationRoundTripRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public ReservationRoundTripController(ReservationRoundTripRepository reservationRoundTripRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.reservationRoundTripRepository = reservationRoundTripRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/reservationRoundTrip")
    public ResponseEntity<?> createReservationRoundTrip(@Nonnull @RequestBody ReservationRoundTripDTO dto) {
        try {
            Optional<Flight> flightOpt = flightRepository.findById(dto.getFlightId());
            Optional<Flight> returnFlightOpt = flightRepository.findById(dto.getReturnFlightId());
            Optional<User> userOpt = userRepository.findById(dto.getUserId());

            if (flightOpt.isEmpty() || returnFlightOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight, return flight, or user IDs");
            }

            ReservationRoundTrip reservation = new ReservationRoundTrip();
            reservation.setFlight(flightOpt.get());
            reservation.setReturnFlight(returnFlightOpt.get());
            reservation.setUser(userOpt.get());
            reservation.setLuggage(dto.getLuggage());
            reservation.setCreatedAt(LocalDateTime.now());
            reservation.setTotal(calcReservationRoundTripTotal(flightOpt.get(), returnFlightOpt.get()));


            reservationRoundTripRepository.save(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    private Double calcReservationRoundTripTotal(Flight departureFlight, Flight returnFlight) {
        return departureFlight.getPrice() + returnFlight.getPrice(); // + fees? nah
    }

    @PutMapping("/reservationRoundTrip/{id}")
    public ResponseEntity<?> updateReservationRoundTrip(@RequestBody ReservationRoundTripDTO dto, @PathVariable Long id) {
        try {
            Optional<ReservationRoundTrip> existingEntityOpt = reservationRoundTripRepository.findById(id);
            if (existingEntityOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            ReservationRoundTrip existingEntity = existingEntityOpt.get();

            Optional<Flight> flightOpt = flightRepository.findById(dto.getFlightId());
            Optional<Flight> returnFlightOpt = flightRepository.findById(dto.getReturnFlightId());
            Optional<User> userOpt = userRepository.findById(dto.getUserId());

            if (flightOpt.isEmpty() || returnFlightOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight, return flight, or user IDs");
            }

            existingEntity.setFlight(flightOpt.get());
            existingEntity.setReturnFlight(returnFlightOpt.get());
            existingEntity.setUser(userOpt.get());
            existingEntity.setLuggage(dto.getLuggage());
            existingEntity.setTotal(existingEntity.getTotal()); //TODO: not a real put method

            reservationRoundTripRepository.save(existingEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update");
        }
    }
}
