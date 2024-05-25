package com.ddr.back.controller;

import com.ddr.back.entity.City;
import com.ddr.back.entity.Country;
import com.ddr.back.repository.CityRepository;
import com.ddr.back.repository.CountryRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    private final CountryRepository repository;

    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/country")
    public List<Country> getCountries() {
        return repository.findAll();
    }

    @GetMapping("/country/{id}")
    public Optional<Country> getCountry(@PathVariable Long id) {
        if (repository.existsById(id)){
            return repository.findById(id);
        }
        return Optional.empty();
    }

    @PostMapping("/country")
    public ResponseEntity<?> createCountry(@Nonnull @RequestBody Country country) {
        if (country.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Country name cannot be empty");
        }

        try {
            repository.save(country);

            return ResponseEntity.status(HttpStatus.CREATED).body("Country created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create country");
        }
    }

    @PutMapping("/country")
    public ResponseEntity<?> updateCountry(@RequestBody Country country){
        if (country.getId() == null){
            return ResponseEntity.badRequest().body("Country ID cannot be null");
        }

        try {
            repository.save(country);
            return ResponseEntity.status(HttpStatus.OK).body("Country updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to updated country");
        }
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("Country ID cannot be null");
        }
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Country deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete country");
        }
    }

}
