package com.ddr.back.controller;

import com.ddr.back.dto.ReservationDTO;
import com.ddr.back.entity.*;
import com.ddr.back.repository.FlightRepository;
import com.ddr.back.repository.ReservationRepository;
import com.ddr.back.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public ReservationController(ReservationRepository reservationRepository, FlightRepository flightRepository, UserRepository userRepository){
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        try {
            List<Reservation> list = reservationRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Optional<Reservation>> getReservation(@PathVariable Long id) {
        try {
            if (!reservationRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(reservationRepository.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservations/user/{id}")
    public ResponseEntity<List<Reservation>> getReservationsFromUser(@PathVariable Long id) {
        try {
            if (!userRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Reservation> list = reservationRepository.findReservationsByUserId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> createReservation(@Nonnull @RequestBody ReservationDTO dto) {
        try {

            Optional<Flight> flightOpt = flightRepository.findById(dto.getFlightId());
            Optional<User> userOpt = userRepository.findById(dto.getUserId());

            if (flightOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight or user IDs");
            }

            Reservation reservation = new Reservation();
            reservation.setFlight(flightOpt.get());
            reservation.setUser(userOpt.get());
            reservation.setLuggage(dto.getLuggage());
            reservation.setCreatedAt(LocalDateTime.now());
            reservation.setTotal(calcReservationRoundTripTotal(flightOpt.get()));

            reservationRepository.save(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    private Double calcReservationRoundTripTotal(Flight flight) {
        return flight.getPrice();
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationDTO dto, @PathVariable Long id) {
        try {

            Optional<Reservation> existingEntityOpt = reservationRepository.findById(id);
            if (existingEntityOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            Reservation existingEntity = existingEntityOpt.get();

            Optional<Flight> flightOpt = flightRepository.findById(dto.getFlightId());
            Optional<User> userOpt = userRepository.findById(dto.getUserId());

            if (flightOpt.isEmpty() || userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight or user IDs");
            }

            existingEntity.setFlight(flightOpt.get());
            existingEntity.setUser(userOpt.get());
            existingEntity.setLuggage(dto.getLuggage());
            existingEntity.setTotal(existingEntity.getTotal()); //TODO: not a real put method

            reservationRepository.save(existingEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update");
        }
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID cannot be null");
        }

        try {
            if (!reservationRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            reservationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
        }
    }
}
