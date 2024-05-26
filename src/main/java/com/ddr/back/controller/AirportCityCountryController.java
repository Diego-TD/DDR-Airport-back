package com.ddr.back.controller;

import com.ddr.back.dto.AirportCityCountryDTO;
import com.ddr.back.entity.Airport;
import com.ddr.back.entity.AirportCityCountry;
import com.ddr.back.entity.City;
import com.ddr.back.entity.Country;
import com.ddr.back.repository.AirportCityCountryRepository;
import com.ddr.back.repository.AirportRepository;
import com.ddr.back.repository.CityRepository;
import com.ddr.back.repository.CountryRepository;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AirportCityCountryController {
    private final AirportCityCountryRepository airportCityCountryRepository;
    private final CountryRepository countryRepository;
    private CityRepository cityRepository;
    private final AirportRepository airportRepository;

    public AirportCityCountryController(AirportCityCountryRepository airportCityCountryRepository, CountryRepository countryRepository, CityRepository cityRepository, AirportRepository airportRepository) {
        this.airportCityCountryRepository = airportCityCountryRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
    }



    @GetMapping("/airportCityCountry")
    public ResponseEntity<List<AirportCityCountry>> getAll() {
        try {
            List<AirportCityCountry> list = airportCityCountryRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/airportCityCountry/{id}")
    public ResponseEntity<Optional<AirportCityCountry>> getById(@PathVariable Long id) {
        try {
            if (!airportCityCountryRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(airportCityCountryRepository.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airportCityCountry/country/{id}")
    public ResponseEntity<List<AirportCityCountry>> getAirportsFromCountry(@PathVariable Long id) {
        try {
            if (!countryRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<AirportCityCountry> list = airportCityCountryRepository.findAllByCountryId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airportCityCountry/city/{id}")
    public ResponseEntity<List<AirportCityCountry>> getAirportsFromCity(@PathVariable Long id) {
        try {
            if (!cityRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<AirportCityCountry> list = airportCityCountryRepository.findAllByCityId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airportCityCountry")
    public ResponseEntity<?> createAirportCityCountry(@Nonnull @RequestBody AirportCityCountryDTO dto) {
        try {
                Optional<Airport> airportOpt = airportRepository.findById(dto.getAirportId());
            Optional<City> cityOpt = cityRepository.findById(dto.getCityId());
            Optional<Country> countryOpt = countryRepository.findById(dto.getCountryId());

            if (airportOpt.isEmpty() || cityOpt.isEmpty() || countryOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid airport, city, or country ID");
            }

            AirportCityCountry airportCityCountry = new AirportCityCountry();
            airportCityCountry.setAirport(airportOpt.get());
            airportCityCountry.setCity(cityOpt.get());
            airportCityCountry.setCountry(countryOpt.get());

            airportCityCountryRepository.save(airportCityCountry);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    @PutMapping("/airportCityCountry/{id}")
    public ResponseEntity<?> updateAirportCityCountry(@PathVariable Long id, @RequestBody AirportCityCountryDTO dto) {
        try {
            Optional<AirportCityCountry> existingEntityOpt = airportCityCountryRepository.findById(id);
            if (existingEntityOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            AirportCityCountry existingEntity = existingEntityOpt.get();
            Optional<Airport> airportOpt = airportRepository.findById(dto.getAirportId());
            Optional<City> cityOpt = cityRepository.findById(dto.getCityId());
            Optional<Country> countryOpt = countryRepository.findById(dto.getCountryId());

            if (airportOpt.isEmpty() || cityOpt.isEmpty() || countryOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid airport, city, or country ID");
            }

            existingEntity.setAirport(airportOpt.get());
            existingEntity.setCity(cityOpt.get());
            existingEntity.setCountry(countryOpt.get());

            airportCityCountryRepository.save(existingEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update");
        }
    }

    @DeleteMapping("/airportCityCountry/{id}")
    public ResponseEntity<?> deleteAirportCityCountry(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("ID cannot be null");
        }

        try {
            if (!airportCityCountryRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
            }

            airportCityCountryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
        }
    }
}
