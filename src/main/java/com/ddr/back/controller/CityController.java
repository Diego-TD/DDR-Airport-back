package com.ddr.back.controller;

import com.ddr.back.entity.City;
import com.ddr.back.repository.CityRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
    private final CityRepository repository;

    public CityController(CityRepository repository){
        this.repository = repository;
    }

    @GetMapping("/city")
    public List<City> getCities() {
        return repository.findAll();
    }

    @GetMapping("/city/{id}")
    public Optional<City> getCity(@PathVariable Long id) {
        if (repository.existsById(id)){
            return repository.findById(id);
        }
        return Optional.empty();
    }

    @PostMapping("/city")
    public ResponseEntity<?> createCity(@Nonnull @RequestBody City city) {
        if (city.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("City name cannot be empty");
        }

        try {
            repository.save(city);

            return ResponseEntity.status(HttpStatus.CREATED).body("City created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create city");
        }
    }

    @PutMapping("/city")
    public ResponseEntity<?> updateCity(@RequestBody City city){
        if (city.getId() == null){
            return ResponseEntity.badRequest().body("City ID cannot be null");
        }

        try {
            repository.save(city);
            return ResponseEntity.status(HttpStatus.OK).body("City updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to updated city");
        }
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("City ID cannot be null");
        }
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("City deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete city");
        }
    }
}
