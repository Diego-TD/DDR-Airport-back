package com.ddr.back.controller;

import com.ddr.back.entity.Airport;
import com.ddr.back.repository.AirportRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

public class AirportController {
    private final AirportRepository repository;

    public AirportController(AirportRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/airport")
    public List<Airport> getAirports() {
        return repository.findAll();
    }

    @GetMapping("/airport/{id}")
    public Optional<Airport> getAirport(@PathVariable Long id) {
        if (repository.existsById(id)){
            return repository.findById(id);
        }
        return null;
    }

    @PostMapping("/airport")
    public ResponseEntity<?> createAirport(@Nonnull @RequestBody Airport airport) {
        if (airport.getName() == null || airport.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Airport name cannot be empty");
        }

        try {
            repository.save(airport);

            return ResponseEntity.status(HttpStatus.CREATED).body("Airport created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create airport");
        }
    }

    @PutMapping("/airport")
    public ResponseEntity<?> updateAirport(@RequestBody Airport airport){
        if (airport.getId() == null){
            return ResponseEntity.badRequest().body("Airport ID cannot be null");
        }

        try {
            repository.save(airport);
            return ResponseEntity.status(HttpStatus.OK).body("Airport updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to updated airport");
        }
    }

    @DeleteMapping("/airport/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("Airport ID cannot be null");
        }
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Airport deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete airport");
        }
    }
}
