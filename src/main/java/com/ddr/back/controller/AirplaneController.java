package com.ddr.back.controller;

import com.ddr.back.dto.AirportCityCountryDTO;
import com.ddr.back.entity.*;
import com.ddr.back.repository.AirplaneRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AirplaneController {
    private final AirplaneRepository airplaneRepository;


    public AirplaneController(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping("/airplanes")
    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        try {
            List<Airplane> list = airplaneRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: optimize endpoints with services to reduce duplicated code like this one
    @GetMapping("/airplane/{id}")
    public ResponseEntity<Optional<Airplane>> getAirplaneById(@PathVariable Long id) {
        try {
            if (!airplaneRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(airplaneRepository.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airplane")
    public ResponseEntity<?> createAirplane(@Nonnull @RequestBody Airplane airplane) {
        try {
            if (airplane.getName().isEmpty() || airplane.getTotalCapacity().describeConstable().isEmpty()
                    || airplane.getTotalStorage().describeConstable().isEmpty()
                    || airplane.getFullCapacity() == null || airplane.getFullStorage() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name, total capacity, total storage, full capacity or full storage");
            }

            airplaneRepository.save(airplane);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    @PutMapping("/airplane/{id}")
    public ResponseEntity<?> updateAirplane(@RequestBody Airplane newAirplane, @PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Airplane ID cannot be null");
            }

            if (newAirplane == null) {
                return ResponseEntity.badRequest().body("Airplane object cannot be null");
            }

            Optional<Airplane> existingEntityOpt = airplaneRepository.findById(id);
            if (existingEntityOpt.isPresent()) {
                Airplane existingEntity = existingEntityOpt.get();
                existingEntity.setName(newAirplane.getName());
                existingEntity.setTotalCapacity(newAirplane.getTotalCapacity());
                existingEntity.setTotalStorage(newAirplane.getTotalStorage());
                existingEntity.setFullCapacity(newAirplane.getFullCapacity());
                existingEntity.setFullStorage(newAirplane.getFullStorage());
                airplaneRepository.save(existingEntity);
                return ResponseEntity.status(HttpStatus.OK).body("Airplane updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airplane not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update airplane");
        }
    }

    @DeleteMapping("/airplane/{id}")
    public ResponseEntity<?> deleteAirplane(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("Airplane ID cannot be null");
        }
        try {
            airplaneRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Airplane deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete airplane");
        }
    }

}
